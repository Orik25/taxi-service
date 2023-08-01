package com.lightweight.taxiservice.service.interfaces;

import com.lightweight.taxiservice.entity.Car;
import com.lightweight.taxiservice.entity.Driver;

import java.util.List;

public interface CarService {
    List<Car> findAll();
    List<Car> findCarsByDriverIdIsNull();
    Car findById(Long id);
    Car save(Car car);
    void deleteById(Long id);
}
