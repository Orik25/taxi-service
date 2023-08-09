package com.lightweight.taxiservice.controller;

import com.lightweight.taxiservice.service.interfaces.CarService;
import com.lightweight.taxiservice.service.interfaces.DriverService;
import com.lightweight.taxiservice.service.interfaces.RoleService;
import com.lightweight.taxiservice.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/system")
public class AdminController {
    private UserService userService;
    private RoleService roleService;
    private CarService carService;
    private DriverService driverService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService, CarService carService,DriverService driverService) {
        this.userService = userService;
        this.roleService = roleService;
        this.carService = carService;
        this.driverService = driverService;

    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "admin/list-users";
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
