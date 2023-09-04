package com.lightweight.taxiservice.controller.RESTcontroller;

import com.lightweight.taxiservice.entity.CarCoordinates;
import com.lightweight.taxiservice.service.interfaces.CarCoordinatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarCoordinatesRESTController {
    private CarCoordinatesService carCoordinatesService;

    @Autowired
    public CarCoordinatesRESTController(CarCoordinatesService carCoordinatesService) {
        this.carCoordinatesService = carCoordinatesService;
    }

    @GetMapping("/carsCoordinates")
    public List<CarCoordinates> getCarsCoordinates(){
        return carCoordinatesService.findAll();
    }

    @GetMapping("/carsCoordinates/{carCoordinatesId}")
    public CarCoordinates getCarCoordinatesById(@PathVariable Long carCoordinatesId){
        return carCoordinatesService.findById(carCoordinatesId);
    }


    @PostMapping("/carsCoordinates")
    public CarCoordinates addCarCoordinatesId(@RequestBody CarCoordinates carCoordinates){
        return carCoordinatesService.save(carCoordinates);
    }

    @PutMapping("/cars/{carCoordinatesId}")
    public CarCoordinates updateDriver(@PathVariable Long carCoordinatesId, @RequestBody CarCoordinates updatedCarCoordinates) {
        updatedCarCoordinates.setId(carCoordinatesId);
        return carCoordinatesService.update(updatedCarCoordinates);
    }

    @DeleteMapping("/cars/{carCoordinatesId}")
    public void deleteDriver(@PathVariable Long carCoordinatesId){
        carCoordinatesService.deleteById(carCoordinatesId);
    }
}
