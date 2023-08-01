package com.lightweight.taxiservice.service.impl;

import com.lightweight.taxiservice.DAO.DriverRepository;
import com.lightweight.taxiservice.entity.Driver;
import com.lightweight.taxiservice.exception.NoDriverFoundException;
import com.lightweight.taxiservice.exception.NoDriversFoundException;
import com.lightweight.taxiservice.service.interfaces.DriverService;
import jakarta.persistence.EntityNotFoundException;
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
        return driverRepository.findById(id)
                .orElseThrow(() -> new NoDriverFoundException("Driver not found with id: " + id));
    }

    @Override
    public Driver save(Driver driver) {
        return driverRepository.save(driver);
    }

    @Override
    public void deleteById(Long id) {
        driverRepository.findById(id)
                .orElseThrow(() -> new NoDriverFoundException("That driver cannot be deleted because " +
                        "driver with id: " + id + " not found"));
        driverRepository.deleteById(id);
    }
}
