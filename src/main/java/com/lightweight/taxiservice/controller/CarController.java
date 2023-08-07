package com.lightweight.taxiservice.controller;

import com.lightweight.taxiservice.entity.Car;
import com.lightweight.taxiservice.service.interfaces.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {
    private CarService carService;
    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public List<Car> getCars(){
        return carService.findAll();
    }

    @GetMapping("/cars/{carId}")
    public Car getCarById(@PathVariable Long carId){
        return carService.findById(carId);
    }

    @GetMapping("/available-cars")
    public List<Car> getAvailableCars(){
        return carService.findCarsByDriverIdIsNull();
    }

    @PostMapping("/cars")
    public Car addCar(@RequestBody Car car){
        return carService.save(car);
    }

    @PutMapping("/cars/{carId}")
    public Car updateDriver(@PathVariable Long carId, @RequestBody Car updatedCar) {
        updatedCar.setId(carId);
        return carService.update(updatedCar);
    }

    @DeleteMapping("/cars/{carId}")
    public void deleteDriver(@PathVariable Long carId){
        Car deletedCar = carService.findById(carId);
        carService.deleteById(carId);
    }
}

