package com.lightweight.taxiservice.controller.RESTcontroller;

import org.springframework.ui.Model;
import com.lightweight.taxiservice.entity.User;
import com.lightweight.taxiservice.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
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
    @PutMapping("/users/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody User updatedUser) {
        updatedUser.setId(userId);
        return userService.update(updatedUser);
    }

    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable Long userId){
        User deletedUser = userService.findById(userId);
        userService.deleteById(userId);
    }
}
