package com.lightweight.taxiservice.controller;

import com.lightweight.taxiservice.DTO.car.CarForNewDriverDTO;
import com.lightweight.taxiservice.DTO.driver.ConverterDriverDTO;
import com.lightweight.taxiservice.DTO.driver.DriverUpdateDTO;
import com.lightweight.taxiservice.DTO.driver.DriverWithCarDTO;
import com.lightweight.taxiservice.DTO.driver.DriverWithOutCarDTO;
import com.lightweight.taxiservice.entity.Driver;
import com.lightweight.taxiservice.service.interfaces.CarService;
import com.lightweight.taxiservice.service.interfaces.DriverService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/system")
public class DriverController {
    private DriverService driverService;
    private CarService carService;
    private ConverterDriverDTO converterDriverDTO;
    private List<Integer> availableYears;
    private List<Integer> availableCapacity;

    @Autowired
    public DriverController(DriverService driverService, CarService carService,
                            ConverterDriverDTO converterDriverDTO) {
        this.driverService = driverService;
        this.carService = carService;
        this.converterDriverDTO = converterDriverDTO;
        this.availableYears = generateIntList(1970, 2023);
        this.availableCapacity = generateIntList(1, 8);
    }

    private List<Integer> generateIntList(int start, int end) {
        return IntStream.rangeClosed(start, end)
                .boxed()
                .collect(Collectors.toList());
    }

    @GetMapping("/drivers")
    public String getDrivers(Model model,
                             @RequestParam(name = "page", defaultValue = "0") int page,
                             @RequestParam(name = "size", defaultValue = "5") int size,
                             @RequestParam(name = "sortField", defaultValue = "id") String sortField,
                             @RequestParam(name = "sortOrder", defaultValue = "asc") String sortOrder){
        model.addAttribute("driversPage", driverService.getAllDriversSorted(page,size,sortField, sortOrder));
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortOrder", sortOrder);
        return "admin/list-drivers";
    }

    @GetMapping("/search-drivers")
    public String searchDriversByLastName(@RequestParam(name = "searchLastName" ) String searchLastName,
                                       @RequestParam(name = "page", defaultValue = "0") int page,
                                       @RequestParam(name = "size", defaultValue = "5") int size,
                                       Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Driver> driversPage = driverService.findDriversByLastNameContainingIgnoreCase(searchLastName, pageable);
        model.addAttribute("searchLastName",searchLastName);
        model.addAttribute("driversPage", driversPage);
        return "admin/list-drivers";
    }

    @GetMapping("/update-driver/{id}")
    public String showUpdateDriverForm(@PathVariable Long id,  Model model) {
        DriverUpdateDTO driver = converterDriverDTO.convertToDTOForUpdate(driverService.findById(id));
        model.addAttribute("driver", driver);
        model.addAttribute("availableCars", carService.findCarsWithoutDrivers());
        return "admin/forms/update-driver";
    }

    @PostMapping("/update-driver/{id}")
    public String updateDriver(@PathVariable Long id,
                            @Valid @ModelAttribute("driver") DriverUpdateDTO driver,
                            BindingResult result, Model model) {
        if (result.hasErrors()) {
            if (result.hasGlobalErrors()) {
                driver.setGlobalError(result.getGlobalError().getDefaultMessage());
            }
            model.addAttribute("driver", driver);
            model.addAttribute("availableCars", carService.findCarsWithoutDrivers());
            return "admin/forms/update-driver";
        }

        driverService.update(id,driver);
        return "redirect:/system/drivers";
    }

    @GetMapping("/add-driver-without-car")
    public String showAddDriverWithOutCarForm(Model model) {
        DriverWithOutCarDTO newDriver = new DriverWithOutCarDTO();
        model.addAttribute("driver", newDriver);
        model.addAttribute("availableCars", carService.findCarsWithoutDrivers());
        return "admin/forms/add-driver-without-car";
    }

    @PostMapping("/add-driver-without-car")
    public String addDriverWithOutCar(@Valid @ModelAttribute("driver") DriverWithOutCarDTO driver,
                                      BindingResult result, Model model){

        if (result.hasErrors()){
            model.addAttribute("driver", driver);
            model.addAttribute("availableCars", carService.findCarsWithoutDrivers());
            return "admin/forms/add-driver-without-car";
        }

        Driver newDriver = converterDriverDTO.convertToEntity(driver);
        driverService.save(newDriver);
        return "redirect:/system/drivers";
    }

    @GetMapping("/add-driver-with-car")
    public String showAddDriverWithCarForm(Model model) {
        DriverWithCarDTO newDriver = new DriverWithCarDTO();

        model.addAttribute("driver", newDriver);
        model.addAttribute("availableYears", availableYears);
        model.addAttribute("availableCapacity",availableCapacity);
        return "admin/forms/add-driver-with-car";
    }

    @PostMapping("/add-driver-with-car")
    public String addDriverWithCar(@Valid @ModelAttribute("driver") DriverWithCarDTO driver,
                                      BindingResult result, Model model){

        if (result.hasErrors()){
            System.out.println(result.getAllErrors());
            model.addAttribute("driver", driver);
            model.addAttribute("availableYears", availableYears);
            model.addAttribute("availableCapacity",availableCapacity);
            return "admin/forms/add-driver-with-car";
        }

        Driver newDriver = converterDriverDTO.convertToEntity(driver);
        driverService.save(newDriver);
        return "redirect:/system/drivers";
    }

    @GetMapping("/delete-driver/{id}")
    public String delete(@PathVariable("id") Long id) {
        driverService.deleteById(id);
        return "redirect:/system/drivers";
    }
}
