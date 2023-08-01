package com.lightweight.taxiservice.service.impl;

import com.lightweight.taxiservice.DAO.CarRepository;
import com.lightweight.taxiservice.entity.Car;
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
        return carRepository.findAll();
    }//todo:implement exception, when there aren`t any cars

    @Override
    public List<Car> findCarsByDriverIdIsNull() {
        return carRepository.findCarsByDriverIdIsNull();
    }//todo:implement exception, when there aren`t any free cars

    @Override
    public Car findById(Long id) {
        return carRepository.findById(id).orElseThrow(()->new NoSuchElementException("todo"));
    }//todo:implement exception, when car doesn`t exists

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public void deleteById(Long id) {
        carRepository.deleteById(id);
    }//todo:implement exception, when car doesn`t exists
}
