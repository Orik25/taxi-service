package com.lightweight.taxiservice.controller;

import com.lightweight.taxiservice.DTO.user.ConverterUserDTO;
import com.lightweight.taxiservice.DTO.user.UserUpdateProfileDTO;
import com.lightweight.taxiservice.entity.User;
import com.lightweight.taxiservice.service.interfaces.CarService;
import com.lightweight.taxiservice.service.interfaces.DriverService;
import com.lightweight.taxiservice.service.interfaces.RoleService;
import com.lightweight.taxiservice.service.interfaces.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/system")
public class UserController {
    private UserService userService;
    private ConverterUserDTO converterUserDTO;

    @Autowired
    public UserController(UserService userService,
                          ConverterUserDTO converterUserDTO) {
        this.userService = userService;
        this.converterUserDTO = converterUserDTO;
    }

    @GetMapping
    public String systemPage() {
        return "/admin/system";
    }

    @GetMapping("/users")
    public String getUsers(Model model,
                           @RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name = "size", defaultValue = "5") int size,
                           @RequestParam(name = "sortField", defaultValue = "id") String sortField,
                           @RequestParam(name = "sortOrder", defaultValue = "asc") String sortOrder) {
        model.addAttribute("usersPage", userService.getAllUsersSorted(page, size, sortField, sortOrder));
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortOrder", sortOrder);
        return "admin/list-users";
    }

    @GetMapping("/update-user/{id}")
    public String showUpdateUserForm(@PathVariable Long id, Model model) {
        UserUpdateProfileDTO userDto = converterUserDTO.convertToDTO(userService.findById(id));
        model.addAttribute("user", userDto);

        return "admin/forms/update-user";
    }

    @GetMapping("/search-users")
    public String searchUsersByLastName(@RequestParam(name = "searchField") String searchField,
                                        @RequestParam(name = "searchValue") String searchValue,
                                        @RequestParam(name = "page", defaultValue = "0") int page,
                                        @RequestParam(name = "size", defaultValue = "5") int size,
                                        Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> usersPage = userService.findByFieldContainingIgnoreCase(searchField, searchValue, pageable);
        model.addAttribute("searchField", searchField);
        model.addAttribute("searchValue", searchValue);
        model.addAttribute("usersPage", usersPage);
        return "admin/list-users";
    }

    @PostMapping("/update-user/{id}")
    public String updateUser(@PathVariable Long id,
                             @Valid @ModelAttribute("user") UserUpdateProfileDTO userProfile,
                             BindingResult result, Model model) {

        if (result.hasErrors()) {
            if (result.hasGlobalErrors()) {
                userProfile.setGlobalError(result.getGlobalError().getDefaultMessage());
            }
            model.addAttribute("user", userProfile);
            return "admin/forms/update-user";
        }

        userService.update(id, userProfile);
        return "redirect:/system/users";
    }

    @GetMapping("/delete-user/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/system/users";
    }


    @GetMapping("/profile")
    public String profilePage(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User admin = userService.findByEmail(userDetails.getUsername());

        model.addAttribute("admin", admin);

        return "/admin/profile-admin";
    }
}
