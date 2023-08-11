package com.lightweight.taxiservice.service.interfaces;

import com.lightweight.taxiservice.entity.Car;
import com.lightweight.taxiservice.entity.CarCoordinates;

import java.util.List;

public interface CarCoordinatesService {
    List<CarCoordinates> findAll();

    CarCoordinates findById(Long id);

    CarCoordinates save(CarCoordinates carCoordinates);

    CarCoordinates update(CarCoordinates carCoordinates);

    void deleteById(Long id);
}
