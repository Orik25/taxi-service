package com.lightweight.taxiservice.service.interfaces;

import com.lightweight.taxiservice.DTO.car.CarUpdateDTO;
import com.lightweight.taxiservice.DTO.driver.DriverUpdateDTO;
import com.lightweight.taxiservice.entity.Car;
import com.lightweight.taxiservice.entity.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DriverService {
    List<Driver> findAll();
    Driver findById(Long id);
    Driver save(Driver driver);
    Driver update(Long id, DriverUpdateDTO updateDriverDTO);
    void deleteById(Long id);
    List<Driver> findDriversWithoutCars();

    Page<Driver> getAllDriversSorted(int page, int size, String sortField, String sortOrder);

    Page<Driver> findDriversByLastNameContainingIgnoreCase(String model, Pageable pageable);
}
