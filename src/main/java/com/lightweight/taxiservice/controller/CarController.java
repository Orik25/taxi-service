package com.lightweight.taxiservice.controller;

import com.lightweight.taxiservice.DTO.car.CarForAddDTO;
import com.lightweight.taxiservice.DTO.car.CarUpdateDTO;
import com.lightweight.taxiservice.DTO.car.ConverterCarDTO;
import com.lightweight.taxiservice.DTO.user.UserUpdateProfileDTO;
import com.lightweight.taxiservice.entity.Car;
import com.lightweight.taxiservice.entity.CarCoordinates;
import com.lightweight.taxiservice.entity.User;
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
public class CarController {
    private CarService carService;
    private DriverService driverService;
    private ConverterCarDTO converterCarDTO;
    private List<Integer> availableYears;
    private List<Integer> availableCapacity;
    @Autowired
    public CarController(CarService carService,
                         ConverterCarDTO converterCarDTO,
                         DriverService driverService) {
        this.carService = carService;
        this.converterCarDTO = converterCarDTO;
        this.driverService = driverService;
        this.availableYears = generateIntList(1970, 2023);
        this.availableCapacity = generateIntList(1, 8);
    }
    private List<Integer> generateIntList(int start, int end) {
        return IntStream.rangeClosed(start, end)
                .boxed()
                .collect(Collectors.toList());
    }

    @GetMapping("/cars")
    public String getUsers(Model model,
                           @RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name = "size", defaultValue = "5") int size,
                           @RequestParam(name = "sortField", defaultValue = "id") String sortField,
                           @RequestParam(name = "sortOrder", defaultValue = "asc") String sortOrder) {
        model.addAttribute("carsPage", carService.getAllCarsSorted(page,size,sortField, sortOrder));
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortOrder", sortOrder);
        return "admin/list-cars";
    }
    @GetMapping("/search-cars")
    public String searchCarsByLastName(@RequestParam(name = "searchField") String searchField,
                                       @RequestParam(name = "searchValue") String searchValue,
                                        @RequestParam(name = "page", defaultValue = "0") int page,
                                        @RequestParam(name = "size", defaultValue = "5") int size,
                                        Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Car> carsPage = carService.findByFieldContainingIgnoreCase(searchField,searchValue,pageable);
        model.addAttribute("searchField", searchField);
        model.addAttribute("searchValue", searchValue);
        model.addAttribute("carsPage", carsPage);
        return "admin/list-cars";
    }
    @GetMapping("/update-car/{id}")
    public String showUpdateCarForm(@PathVariable Long id,  Model model) {
        CarUpdateDTO car = converterCarDTO.convertToDTO(carService.findById(id));
        model.addAttribute("car", car);
        model.addAttribute("availableDrivers", driverService.findDriversWithoutCars());
        model.addAttribute("availableYears", availableYears);
        model.addAttribute("availableCapacity",availableCapacity);
        return "admin/forms/update-car";
    }

    @GetMapping("/add-car")
    public String showAddCarForm(Model model){
        CarForAddDTO car = new CarForAddDTO();
        model.addAttribute("car",car);
        model.addAttribute("availableDrivers", driverService.findDriversWithoutCars());
        model.addAttribute("availableYears", availableYears);
        model.addAttribute("availableCapacity",availableCapacity);
        return "admin/forms/add-car";
    }
    @PostMapping("/update-car/{id}")
    public String updateCar(@PathVariable Long id,
                             @Valid @ModelAttribute("car") CarUpdateDTO car,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("car", car);
            model.addAttribute("availableDrivers", driverService.findDriversWithoutCars());
            model.addAttribute("availableYears", availableYears);
            model.addAttribute("availableCapacity",availableCapacity);
            return "admin/forms/update-car";
        }

        carService.update(id,car);
        return "redirect:/system/cars";
    }
    @PostMapping("/add-car")
    public String addCar(@Valid @ModelAttribute("car") CarForAddDTO car,
                         BindingResult result,
                         Model model) {
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            model.addAttribute("car", car);
            model.addAttribute("availableDrivers", driverService.findDriversWithoutCars());
            model.addAttribute("availableYears", availableYears);
            model.addAttribute("availableCapacity",availableCapacity);
            return "admin/forms/add-car";
        }
        carService.save(converterCarDTO.convertToEntity(car));
        return "redirect:/system/cars";
    }

    @GetMapping("/delete-car/{id}")
    public String delete(@PathVariable("id") Long id) {
        carService.deleteById(id);
        return "redirect:/system/cars";
    }
}
