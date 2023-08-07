package com.lightweight.taxiservice.service.interfaces;

import com.lightweight.taxiservice.DAO.UserRepository;
import com.lightweight.taxiservice.entity.Car;
import com.lightweight.taxiservice.entity.User;

import java.util.List;

public interface UserService{
    List<User> findAll();
    List<User> findUsersByRoleName(String role);
    User findById(Long id);
    User findByEmail(String email);
    User save(User user);
    User update(User user);
    void deleteById(Long id);

    User registerUser(User user);
}
