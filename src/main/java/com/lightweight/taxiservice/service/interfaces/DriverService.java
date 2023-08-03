package com.lightweight.taxiservice.service.interfaces;

import com.lightweight.taxiservice.entity.Driver;

import java.util.List;

public interface DriverService {
    List<Driver> findAll();
    Driver findById(Long id);
    Driver save(Driver driver);
    void deleteById(Long id);
}
