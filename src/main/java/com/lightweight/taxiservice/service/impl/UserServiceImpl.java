package com.lightweight.taxiservice.service.impl;

import com.lightweight.taxiservice.DAO.UserRepository;
import com.lightweight.taxiservice.entity.Car;
import com.lightweight.taxiservice.entity.Driver;
import com.lightweight.taxiservice.entity.Role;
import com.lightweight.taxiservice.entity.User;
import com.lightweight.taxiservice.exception.NoUserFoundException;
import com.lightweight.taxiservice.exception.NoUsersFoundException;
import com.lightweight.taxiservice.service.interfaces.RoleService;
import com.lightweight.taxiservice.service.interfaces.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Override
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        if (!users.isEmpty()) {
            return users;
        } else {
            throw new NoUsersFoundException("There are no users exist");
        }
    }

    @Override
    public List<User> findUsersByRoleName(String role) {
        List<User> users = userRepository.findUsersByRoleName(role);
        if (!users.isEmpty()) {
            return users;
        } else {
            throw new NoUsersFoundException("There are no users exist with role: " + role);
        }
    }

    @Override
    public User findById(Long id) {
        isDatabaseEmpty();
        return userRepository.findById(id)
                .orElseThrow(() -> new NoUserFoundException("User not found with id: " + id));
    }

    @Override
    public User findByEmail(String email) {
        isDatabaseEmpty();
        return getOptionalUserByEmail(email)
                .orElseThrow(() -> new NoUserFoundException("User not found with that email " + email));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        isDatabaseEmpty();
        Long id = user.getId();
        userRepository.findById(id)
                .orElseThrow(() -> new NoUserFoundException("Impossible to update the User. User not found with id: " + id));
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        isDatabaseEmpty();
        userRepository.findById(id)
                .orElseThrow(() -> new NoUserFoundException("Impossible to delete the User. User not found with id: " + id));

        userRepository.deleteById(id);
    }

    private Optional<User> getOptionalUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public User registerUser(User user) {
        boolean isPresent = userRepository.findByEmail(user.getEmail()).isPresent();
        if (isPresent){
            throw new IllegalArgumentException("The user with this email already exists");
        }

        user.setRole(roleService.findById(2L));
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    private void isDatabaseEmpty() {
        userRepository.findFirstByOrderByIdAsc()
                .orElseThrow(() -> new NoUserFoundException("Data base has not any records of users"));
    }
}
