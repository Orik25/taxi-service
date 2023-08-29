package com.lightweight.taxiservice.service.impl;

import com.lightweight.taxiservice.DAO.UserRepository;
import com.lightweight.taxiservice.DTO.user.ConverterUserDTO;
import com.lightweight.taxiservice.DTO.user.UserRegistrationDTO;
import com.lightweight.taxiservice.DTO.user.UserUpdateProfileDTO;
import com.lightweight.taxiservice.entity.Role;
import com.lightweight.taxiservice.entity.User;
import com.lightweight.taxiservice.exception.NoUserFoundException;
import com.lightweight.taxiservice.exception.NoUsersFoundException;
import com.lightweight.taxiservice.service.interfaces.RoleService;
import com.lightweight.taxiservice.service.interfaces.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;
    private ConverterUserDTO userConverter;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleService roleService
            , ConverterUserDTO userConverter) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.userConverter = userConverter;
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
    public User update(Long id, UserUpdateProfileDTO userProfile) {
        isDatabaseEmpty();

        User updatedUser = userConverter.convertToEntity(userProfile, findById(id));

        return userRepository.save(updatedUser);
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
    public User registerUser(UserRegistrationDTO user) {
        boolean isPresent = userRepository.findByEmail(user.getEmail()).isPresent();
        if (isPresent) {
            throw new IllegalArgumentException("The user with this email already exists");
        }

        return userRepository.save(userConverter.convertToEntity(user));
    }

    @Override
    public List<User> getAllUsersSorted(String sortField, String sortOrder) {
        Sort sort = Sort.by(sortField);
        if ("desc".equals(sortOrder)) {
            sort = sort.descending();
        }
        return userRepository.findAll(sort);
    }

    @Override
    public List<User> findByLastNameContainingIgnoreCase(String lastName) {

        return userRepository.findUsersByLastNameContainingIgnoreCase(lastName);

    }

    private void isDatabaseEmpty() {
        userRepository.findFirstByOrderByIdAsc()
                .orElseThrow(() -> new NoUserFoundException("Data base has not any records of users"));
    }
}
