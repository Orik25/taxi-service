package com.lightweight.taxiservice.service.impl;

import com.lightweight.taxiservice.DAO.CarRepository;
import com.lightweight.taxiservice.entity.Car;
import com.lightweight.taxiservice.entity.Driver;
import com.lightweight.taxiservice.exception.*;
import com.lightweight.taxiservice.service.interfaces.CarService;
import jakarta.persistence.Transient;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        isDatabaseEmpty();
        return carRepository.findById(id).orElseThrow(() ->
                new NoCarFoundException("Car not found with id: " + id));
    }

    @Override
    public Car update(Car car) {
        isDatabaseEmpty();
        Long id = car.getId();
        carRepository.findById(id)
                .orElseThrow(()->new NoCarFoundException("Impossible to update the Car. Car not found with id: " + id));
        return carRepository.save(car);
    }

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public void deleteById(Long id) {
        isDatabaseEmpty();
        Car car = carRepository.findById(id)
                .orElseThrow(()->new NoCarFoundException("Impossible to delete the Car. Car not found with id: " + id));

        Driver driver = car.getDriver();
        if (driver != null) {
            driver.setCar(null);
        }
        carRepository.delete(car);
    }

    @Override
    public List<Car> getAvailableCarsForOrder() {
        List<Car> cars = carRepository.findAllByStatus("available");
        if (!cars.isEmpty()) {
            return cars;
        } else {
            throw new NoAvailableCarsException("There are no available cars for order");
        }
    }

    private void isDatabaseEmpty() {
        carRepository.findFirstByOrderByIdAsc()
                .orElseThrow(() -> new NoCarFoundException("Data base has not any records of cars"));
    }
}
