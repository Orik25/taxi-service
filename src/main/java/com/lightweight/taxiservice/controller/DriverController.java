package com.lightweight.taxiservice.controller;

import com.lightweight.taxiservice.entity.Driver;
import com.lightweight.taxiservice.DAO.CarRepository;
import com.lightweight.taxiservice.DAO.DriverRepository;
import com.lightweight.taxiservice.service.interfaces.DriverService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DriverController {
    private DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping("/drivers")
    public List<Driver> getDrivers(){
        return driverService.findAll();
    }

    @PostMapping("/drivers")
    public Driver addDriver(@RequestBody Driver driver){
        return driverService.save(driver);
    }

    @PutMapping("/drivers/{driverId}")
    public Driver updateDriver(@PathVariable Long driverId, @RequestBody Driver updatedDriver) {
        updatedDriver.setId(driverId);
        return driverService.save(updatedDriver);
    }

    @DeleteMapping("/drivers/{driverId}")
    public void deleteDriver(@PathVariable Long driverId){
        Driver deletedDriver = driverService.findById(driverId);
        driverService.deleteById(driverId);
    }
}
