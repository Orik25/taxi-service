package com.lightweight.taxiservice.controller.RESTcontroller;

import com.lightweight.taxiservice.DTO.driver.ConverterDriverDTO;
import com.lightweight.taxiservice.DTO.driver.DriverWithCarDTO;
import com.lightweight.taxiservice.DTO.driver.DriverWithOutCarDTO;
import com.lightweight.taxiservice.entity.Driver;
import com.lightweight.taxiservice.service.interfaces.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class DriverController {
    private DriverService driverService;
    private ConverterDriverDTO converterDriverDTO;

    @Autowired
    public DriverController(DriverService driverService,
                            ConverterDriverDTO converterDriverDTO) {
        this.driverService = driverService;
        this.converterDriverDTO = converterDriverDTO;
    }

    @GetMapping("/drivers")
    public List<Driver> getDrivers(){
        return driverService.findAll();
    }

    @GetMapping("/drivers/{driverId}")
    public Driver getDriverById(@PathVariable Long driverId){
        return driverService.findById(driverId);
    }

    @PostMapping("/driver-with-car")
    public Driver addDriver(@RequestBody DriverWithCarDTO driverWithCarDTO){
        Driver newDriver = converterDriverDTO.convertToEntity(driverWithCarDTO);
        return driverService.save(newDriver);
    }

    @PostMapping("/driver-without-car")
    public Driver addDriver(@RequestBody DriverWithOutCarDTO driverWithOutCarDTO){
        Driver newDriver = converterDriverDTO.convertToEntity(driverWithOutCarDTO);
        return driverService.save(newDriver);
    }

    @PutMapping("/drivers/{driverId}")
    public Driver updateDriver(@PathVariable Long driverId, @RequestBody Driver updatedDriver) {
        updatedDriver.setId(driverId);
        return driverService.update(updatedDriver);
    }

    @DeleteMapping("/drivers/{driverId}")
    public void deleteDriver(@PathVariable Long driverId){
        Driver deletedDriver = driverService.findById(driverId);
        driverService.deleteById(driverId);
    }
}
