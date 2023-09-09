package com.lightweight.taxiservice.service.interfaces;

import com.lightweight.taxiservice.DTO.car.CarForUpdateDriverDTO;
import com.lightweight.taxiservice.DTO.car.CarUpdateDTO;
import com.lightweight.taxiservice.entity.Car;
import com.lightweight.taxiservice.entity.Driver;
import com.lightweight.taxiservice.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarService {
    List<Car> findAll();
    List<Car> findCarsByDriverIdIsNull();
    Car findById(Long id);
    Car save(Car car);

    Car update(Long id, CarUpdateDTO updateCarDTO);
    void deleteById(Long id);
    Page<Car> getAllCarsSorted(int page, int size, String sortField, String sortOrder);
    List<Car> getAvailableCarsForOrder();
    Page<Car> findCarsByModelContainingIgnoreCase(String model, Pageable pageable);

    List<Car> findCarsWithoutDrivers();
    Page<Car> findByFieldContainingIgnoreCase(String fieldName,String searchValue, Pageable pageable);
}
