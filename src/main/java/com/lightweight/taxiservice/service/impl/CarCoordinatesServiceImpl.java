package com.lightweight.taxiservice.service.impl;

import com.lightweight.taxiservice.DAO.CarCoordinatesRepository;
import com.lightweight.taxiservice.entity.Car;
import com.lightweight.taxiservice.entity.CarCoordinates;
import com.lightweight.taxiservice.entity.Driver;
import com.lightweight.taxiservice.exception.NoCarCoordinatesFoundException;
import com.lightweight.taxiservice.exception.NoCarFoundException;
import com.lightweight.taxiservice.exception.NoDriverFoundException;
import com.lightweight.taxiservice.exception.NoListOfCarCoordinatesFoundException;
import com.lightweight.taxiservice.service.interfaces.CarCoordinatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarCoordinatesServiceImpl implements CarCoordinatesService {
    private CarCoordinatesRepository carCoordinatesRepository;

    @Autowired
    public CarCoordinatesServiceImpl(CarCoordinatesRepository carCoordinatesRepository) {
        this.carCoordinatesRepository = carCoordinatesRepository;
    }

    @Override
    public List<CarCoordinates> findAll() {
        List<CarCoordinates> carsCoordinates = carCoordinatesRepository.findAll();
        if(!carsCoordinates.isEmpty()){
            return carsCoordinates;
        }
        else {
            throw new NoListOfCarCoordinatesFoundException("There are no carsCoordinates exist");
        }
    }

    @Override
    public CarCoordinates findById(Long id) {
        isDatabaseEmpty();
        return carCoordinatesRepository.findById(id)
                .orElseThrow(()->new NoCarCoordinatesFoundException("CarCoordinates not found with id:" + id));
    }

    @Override
    public CarCoordinates save(CarCoordinates carCoordinates) {
        return carCoordinatesRepository.save(carCoordinates);
    }

    @Override
    public CarCoordinates update(CarCoordinates carCoordinates) {
        isDatabaseEmpty();
        Long id = carCoordinates.getId();
        carCoordinatesRepository.findById(id)
                .orElseThrow(()->new NoCarFoundException("Impossible to update the CarCoordinates. CarCoordinates not found with id: " + id));
        return carCoordinatesRepository.save(carCoordinates);
    }

    @Override
    public void deleteById(Long id) {
        isDatabaseEmpty();
        CarCoordinates carCoordinates = carCoordinatesRepository.findById(id)
                .orElseThrow(()->new NoCarFoundException("Impossible to delete the CarCoordinates. CarCoordinates not found with id: " + id));
        carCoordinatesRepository.delete(carCoordinates);
    }
    private void isDatabaseEmpty() {
        carCoordinatesRepository.findFirstByOrderByIdAsc()
                .orElseThrow(() -> new NoListOfCarCoordinatesFoundException("Data base has not any records of CarCoordinates"));
    }
}
