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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/system")
public class AdminController {
    private UserService userService;
    private RoleService roleService;
    private CarService carService;
    private DriverService driverService;
    private ConverterUserDTO converterUserDTO;

    @Autowired
    public AdminController(UserService userService, RoleService roleService,
                           CarService carService, DriverService driverService,
                           ConverterUserDTO converterUserDTO) {
        this.userService = userService;
        this.roleService = roleService;
        this.carService = carService;
        this.driverService = driverService;
        this.converterUserDTO = converterUserDTO;
    }

    @GetMapping
    public String systemPage() {
        return "/pages/system";
    }

    @GetMapping("/users")
    public String getUsers(Model model,
                           @RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name = "size", defaultValue = "5") int size,
                           @RequestParam(name = "sortField", defaultValue = "id") String sortField,
                           @RequestParam(name = "sortOrder", defaultValue = "asc") String sortOrder) {
        model.addAttribute("usersPage", userService.getAllUsersSorted(page,size,sortField, sortOrder));
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortOrder", sortOrder);
        return "pages/list-users";
    }

    @GetMapping("/update-user/{id}")
    public String showUpdateUserForm(@PathVariable Long id,  Model model) {
        UserUpdateProfileDTO userDto =  converterUserDTO.convertToDTO(userService.findById(id));
        model.addAttribute("user", userDto);

        return "pages/update-user";
    }
    @GetMapping("/search-users")
    public String searchUsersByLastName(@RequestParam(name = "searchLastName" ) String searchLastName,
                                        @RequestParam(name = "page", defaultValue = "0") int page,
                                        @RequestParam(name = "size", defaultValue = "5") int size,
                                        Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> usersPage = userService.findByLastNameContainingIgnoreCase(searchLastName, pageable);
        model.addAttribute("searchLastName",searchLastName);
        model.addAttribute("usersPage", usersPage);
        return "pages/list-users";
    }

    @PostMapping("/update-user/{id}")
    public String updateUser(@PathVariable Long id,
                             @Valid @ModelAttribute("user") UserUpdateProfileDTO userProfile,
                             BindingResult result,  Model model) {

        if (result.hasErrors()) {
            if (result.hasGlobalErrors()) {
                userProfile.setGlobalError(result.getGlobalError().getDefaultMessage());
            }
            model.addAttribute("user", userProfile);
            return "pages/update-user";
        }

        userService.update(id, userProfile);
        return "redirect:/system/users";
    }

    @GetMapping("/delete-user/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/system/users";
    }

    @GetMapping("/roles")
    public String getRoles(Model model) {
        model.addAttribute("roles", roleService.findAll());
        return "admin/list-roles";
    }

    @GetMapping("/cars")
    public String getCars(Model model) {
        model.addAttribute("cars", carService.findAll());
        return "admin/list-cars";
    }

    @GetMapping("/drivers")
    public String getDrivers(Model model) {
        model.addAttribute("drivers", driverService.findAll());
        return "admin/list-drivers";
    }
}
