package com.lightweight.taxiservice.DTO.user;

import com.lightweight.taxiservice.DAO.UserRepository;
import com.lightweight.taxiservice.entity.Role;
import com.lightweight.taxiservice.entity.User;
import com.lightweight.taxiservice.service.interfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ConverterUserDTO {
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;

    public ConverterUserDTO() {
    }

    @Autowired
    public ConverterUserDTO(RoleService roleService, PasswordEncoder passwordEncoder) {
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    public User convertToEntity(UserUpdateProfileDTO updatedUser, User user){
        user.setEmail(updatedUser.getEmail());
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setPhone(updatedUser.getPhone());

        Role role = roleService.findByName(updatedUser.getRoleName());
        user.setRole(role);

        return user;
    }

    public User convertToEntity(UserRegistrationDTO userReg){
        User newUser = new User();
        newUser.setEmail(userReg.getEmail());
        newUser.setFirstName(userReg.getFirstName());
        newUser.setLastName(userReg.getLastName());
        newUser.setPhone(userReg.getPhone());

        newUser.setRole(roleService.findById(2L));
        newUser.setPassword(passwordEncoder.encode(userReg.getPassword()));

        newUser.setOrders(new ArrayList<>());

        return newUser;
    }

    public UserUpdateProfileDTO convertToDTO(User user){
        UserUpdateProfileDTO updatedUser = new UserUpdateProfileDTO();
        updatedUser.setId(user.getId());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setPhone(user.getPhone());
        updatedUser.setRoleName(user.getRole().getName());

        return updatedUser;
    }
}
