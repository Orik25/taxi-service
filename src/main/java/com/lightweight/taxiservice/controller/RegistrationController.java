package com.lightweight.taxiservice.controller;

import com.lightweight.taxiservice.DTO.user.UserRegistrationDTO;
import com.lightweight.taxiservice.entity.User;
import com.lightweight.taxiservice.service.interfaces.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserRegistrationDTO());
        return "registration";
    }


    @PostMapping
    public String registerUser(@Valid @ModelAttribute("user") UserRegistrationDTO user,
                               BindingResult theBindingResult) {
        if (theBindingResult.hasErrors()) {
            return "registration";
        } else {
            userService.registerUser(user);
            return "redirect:/login";
        }
    }
}
