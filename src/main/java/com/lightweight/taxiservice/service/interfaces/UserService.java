package com.lightweight.taxiservice.service.interfaces;

import com.lightweight.taxiservice.DTO.user.UserRegistrationDTO;
import com.lightweight.taxiservice.DTO.user.UserUpdateProfileDTO;
import com.lightweight.taxiservice.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService{
    List<User> findAll();
    List<User> findUsersByRoleName(String role);
    User findById(Long id);
    User findByEmail(String email);
    User save(User user);
    User update(Long id, UserUpdateProfileDTO userProfile);
    void deleteById(Long id);

    User registerUser(UserRegistrationDTO user);
    public List<User> getAllUsersSorted(String sortField, String sortOrder);
    List<User> findByLastNameContainingIgnoreCase(String lastName);
}
