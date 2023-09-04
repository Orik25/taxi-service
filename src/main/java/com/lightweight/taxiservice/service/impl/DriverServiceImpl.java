package com.lightweight.taxiservice.service.impl;

import com.lightweight.taxiservice.DAO.DriverRepository;
import com.lightweight.taxiservice.entity.Car;
import com.lightweight.taxiservice.entity.Driver;
import com.lightweight.taxiservice.exception.NoCarFoundException;
import com.lightweight.taxiservice.exception.NoDriverFoundException;
import com.lightweight.taxiservice.exception.NoDriversFoundException;
import com.lightweight.taxiservice.service.interfaces.DriverService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Transient;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {
    private DriverRepository driverRepository;

    @Autowired
    public DriverServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public List<Driver> findAll() {
        List<Driver> drivers = driverRepository.findAll();
        if (!drivers.isEmpty()) {
            return drivers;
        } else {
            throw new NoDriversFoundException("There are no drivers exist");
        }
    }

    @Override
    public Driver findById(Long id) {
        isDatabaseEmpty();
        return driverRepository.findById(id)
                .orElseThrow(() -> new NoDriverFoundException("Driver not found with id: " + id));
    }

    @Override
    public Driver save(Driver driver) {
        return driverRepository.save(driver);
    }

    @Override
    public Driver update(Driver driver) {
        isDatabaseEmpty();
        Long id = driver.getId();
        driverRepository.findById(id)
                .orElseThrow(() -> new NoDriverFoundException("Impossible to update the Driver. Driver not found with id: " + id));
        return driverRepository.save(driver);
    }

    @Override
    public void deleteById(Long id) {
        isDatabaseEmpty();
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new NoDriverFoundException("Impossible to delete the Driver. Driver not found with id: " + id));

        Car car = driver.getCar();
        if (car != null) {
            car.setDriver(null);
        }
        driverRepository.delete(driver);
    }

    @Override
    public List<Driver> findDriversWithoutCars() {
        return driverRepository.findDriversWithoutCars();
    }

    private void isDatabaseEmpty() {
        driverRepository.findFirstByOrderByIdAsc()
                .orElseThrow(() -> new NoDriverFoundException("Data base has not any records of drivers"));
    }
}
