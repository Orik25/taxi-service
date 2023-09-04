package com.lightweight.taxiservice.controller.RESTcontroller;

import com.lightweight.taxiservice.DTO.user.UserUpdateProfileDTO;
import com.lightweight.taxiservice.entity.User;
import com.lightweight.taxiservice.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserRESTController {
    private UserService userService;
    @Autowired
    public UserRESTController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.findAll();
    }

    @GetMapping("/users/{usersRole}")
    public List<User> getUsersByRole(@PathVariable String usersRole){
        return userService.findUsersByRoleName(usersRole);
    }

    @GetMapping("/user/{userId}")
    public User getUserById(@PathVariable Long userId){
        return userService.findById(userId);
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User user){
        return userService.save(user);
    }
    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody UserUpdateProfileDTO updatedUser) {
        return userService.update(id, updatedUser);
    }

    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable Long userId){
        User deletedUser = userService.findById(userId);
        userService.deleteById(userId);
    }
}
