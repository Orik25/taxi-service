package com.lightweight.taxiservice.service.impl;

import com.lightweight.taxiservice.DAO.CarRepository;
import com.lightweight.taxiservice.entity.Car;
import com.lightweight.taxiservice.entity.Driver;
import com.lightweight.taxiservice.exception.NoAvailableCarsException;
import com.lightweight.taxiservice.exception.NoCarFoundException;
import com.lightweight.taxiservice.exception.NoCarsFoundException;
import com.lightweight.taxiservice.exception.NoDriversFoundException;
import com.lightweight.taxiservice.service.interfaces.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CarServiceImpl implements CarService {
    private CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> findAll() {
        List<Car> cars = carRepository.findAll();
        if (!cars.isEmpty()) {
            return cars;
        } else {
            throw new NoCarsFoundException("There are no cars exist");
        }
    }

    @Override
    public List<Car> findCarsByDriverIdIsNull() {
        List<Car> cars = carRepository.findCarsByDriverIdIsNull();
        if (!cars.isEmpty()) {
            return cars;
        } else {
            throw new NoAvailableCarsException("There are no available cars exist");
        }
    }

    @Override
    public Car findById(Long id) {
        return carRepository.findById(id).orElseThrow(()->new NoCarFoundException("Car not found with id: " + id));
    }

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public void deleteById(Long id) {
        carRepository.findById(id).orElseThrow(()->new NoCarFoundException("That car cannot be deleted because " +
                "car with id: " + id + " not found"));
        carRepository.deleteById(id);
    }
}
