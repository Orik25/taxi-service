package com.lightweight.taxiservice.controller.RESTcontroller;

import com.lightweight.taxiservice.DTO.car.CarUpdateDTO;
import com.lightweight.taxiservice.entity.Car;
import com.lightweight.taxiservice.service.interfaces.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CarRESTController {
    private CarService carService;
    @Autowired
    public CarRESTController(CarService carService) {
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

    @GetMapping("/available-cars-for-order")
    public List<Car> getAvailableCarsForOrder(){
        return carService.getAvailableCarsForOrder();
    }

    @PostMapping("/cars")
    public Car addCar(@RequestBody Car car){
        return carService.save(car);
    }

    @PutMapping("/cars/{carId}")
    public Car updateCar(@PathVariable Long carId, @RequestBody CarUpdateDTO updatedCar) {
        return carService.update(carId, updatedCar);
    }

    @DeleteMapping("/cars/{carId}")
    public void deleteCar(@PathVariable Long carId){
        Car deletedCar = carService.findById(carId);
        carService.deleteById(carId);
    }
}

