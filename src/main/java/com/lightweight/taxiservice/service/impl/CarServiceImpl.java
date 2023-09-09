package com.lightweight.taxiservice.service.impl;

import com.lightweight.taxiservice.DAO.CarRepository;
import com.lightweight.taxiservice.DTO.car.CarForUpdateDriverDTO;
import com.lightweight.taxiservice.DTO.car.CarUpdateDTO;
import com.lightweight.taxiservice.DTO.car.ConverterCarDTO;
import com.lightweight.taxiservice.entity.Car;
import com.lightweight.taxiservice.entity.Driver;
import com.lightweight.taxiservice.exception.*;
import com.lightweight.taxiservice.service.interfaces.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    private CarRepository carRepository;
    private ConverterCarDTO converterCarDTO;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, ConverterCarDTO converterCarDTO) {
        this.carRepository = carRepository;
        this.converterCarDTO = converterCarDTO;
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
    public Car update(Long id, CarUpdateDTO updateCarDTO) {
        isDatabaseEmpty();
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new NoCarFoundException("Impossible to update the Car. Car not found with id: " + id));
        Car updatedCar = converterCarDTO.convertToEntity(updateCarDTO, car);

        return carRepository.save(updatedCar);
    }

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public void deleteById(Long id) {
        isDatabaseEmpty();
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new NoCarFoundException("Impossible to delete the Car. Car not found with id: " + id));

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

    @Override
    public Page<Car> getAllCarsSorted(int page, int size, String sortField, String sortOrder) {
        Sort sort = Sort.by(sortField);
        if ("desc".equals(sortOrder)) {
            sort = sort.descending();
        }
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        return carRepository.findAll(pageRequest);
    }

    @Override
    public Page<Car> findCarsByModelContainingIgnoreCase(String model, Pageable pageable) {
        return carRepository.findCarsByModelContainingIgnoreCase(model, pageable);
    }

    @Override
    public List<Car> findCarsWithoutDrivers() {
        return carRepository.findCarsWithoutDrivers();
    }

    @Override
    public Page<Car> findByFieldContainingIgnoreCase(String fieldName, String searchValue, Pageable pageable) {
        return carRepository.findByFieldContainingIgnoreCase(fieldName,searchValue,pageable);
    }

    private void isDatabaseEmpty() {
        carRepository.findFirstByOrderByIdAsc()
                .orElseThrow(() -> new NoCarFoundException("Data base has not any records of cars"));
    }
}
