package com.lightweight.taxiservice.service.impl;

import com.lightweight.taxiservice.DAO.UserRepository;
import com.lightweight.taxiservice.entity.Car;
import com.lightweight.taxiservice.entity.Driver;
import com.lightweight.taxiservice.entity.User;
import com.lightweight.taxiservice.exception.NoDriversFoundException;
import com.lightweight.taxiservice.exception.NoUserFoundException;
import com.lightweight.taxiservice.exception.NoUsersFoundException;
import com.lightweight.taxiservice.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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
        return userRepository.findById(id)
                .orElseThrow(() -> new NoUserFoundException("User not found with id: " + id));
    }

    @Override
    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new NoUserFoundException("User not found with email: " + email);
        }
        return user;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new NoUserFoundException("That user cannot be deleted because " +
                        "user with id: " + id + " not found"));
        userRepository.deleteById(id);
    }
}
