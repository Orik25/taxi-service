package com.lightweight.taxiservice.service.interfaces;

import com.lightweight.taxiservice.DTO.user.UserRegistrationDTO;
import com.lightweight.taxiservice.DTO.user.UserUpdateProfileDTO;
import com.lightweight.taxiservice.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
    Page<User> getAllUsersSorted(int page, int size, String sortField, String sortOrder);
    Page<User> findByFieldContainingIgnoreCase(String searchField, String searchValue, Pageable pageable);
}
