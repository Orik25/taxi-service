package com.lightweight.taxiservice.DTO.car;

import com.lightweight.taxiservice.DAO.DriverRepository;
import com.lightweight.taxiservice.DTO.driver.DriverForUpdateCarDTO;
import com.lightweight.taxiservice.entity.Car;
import com.lightweight.taxiservice.entity.CarCoordinates;
import com.lightweight.taxiservice.exception.NoCarFoundException;
import com.lightweight.taxiservice.exception.NoDriverFoundException;
import com.lightweight.taxiservice.service.interfaces.CarCoordinatesService;
import com.lightweight.taxiservice.service.interfaces.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConverterCarDTO {
    private DriverRepository driverRepository;
    private CarCoordinatesService carCoordinatesService;

    @Autowired
    public ConverterCarDTO(DriverRepository driverRepository,CarCoordinatesService carCoordinatesService) {
        this.driverRepository = driverRepository;
        this.carCoordinatesService = carCoordinatesService;
    }

    public Car convertToEntity(CarUpdateDTO carUpdateDTO, Car car){
        car.setBrand(carUpdateDTO.getBrand());
        car.setModel(carUpdateDTO.getModel());
        car.setYear(carUpdateDTO.getYear());
        car.setCategory(carUpdateDTO.getCategory());
        car.setStatus(carUpdateDTO.getStatus());
        car.setCapacity(carUpdateDTO.getCapacity());

        if(carUpdateDTO.getDriver().getId() != null){
            car.setDriver(driverRepository.findById(carUpdateDTO.getDriver().getId()).orElseThrow(() ->
                    new NoDriverFoundException("Driver not found")));
        }
        else{
            car.setDriver(null);
        }

        car.setCarCoordinates(carCoordinatesService.findById(carUpdateDTO.getCoordinatesId()));
        return car;
    }

    public Car convertToEntity(CarForAddDTO car){
        Car newCar = new Car();
        CarCoordinates carCoordinates = new CarCoordinates(0.0,0.0);
        carCoordinates.setCar(newCar);
        newCar.setCarCoordinates(carCoordinates);
        newCar.setBrand(car.getBrand());
        newCar.setModel(car.getModel());
        newCar.setYear(car.getYear());
        newCar.setCategory(car.getCategory());
        newCar.setStatus(car.getStatus());
        newCar.setCapacity(car.getCapacity());

        if(car.getDriver().getId() != null){
            newCar.setDriver(driverRepository.findById(car.getDriver().getId()).orElseThrow(() ->
                    new NoDriverFoundException("Driver not found")));
        }
        else{
            newCar.setDriver(null);
        }

        return newCar;
    }
    public CarUpdateDTO convertToDTO(Car car){
        CarUpdateDTO newCarUpdateDTO = new CarUpdateDTO();
        newCarUpdateDTO.setId(car.getId());
        newCarUpdateDTO.setBrand(car.getBrand());
        newCarUpdateDTO.setModel(car.getModel());
        newCarUpdateDTO.setYear(car.getYear());
        newCarUpdateDTO.setCategory(car.getCategory());
        newCarUpdateDTO.setStatus(car.getStatus());
        newCarUpdateDTO.setCapacity(car.getCapacity());
        if(car.getDriver() != null){
            DriverForUpdateCarDTO driver = new DriverForUpdateCarDTO();
            driver.setId(car.getDriver().getId());
            driver.setLastName(car.getDriver().getLastName());
            driver.setFirstName(car.getDriver().getFirstName());

            newCarUpdateDTO.setDriver(driver);
        }
        else{
            newCarUpdateDTO.setDriver(null);
        }
        newCarUpdateDTO.setCoordinatesId(car.getCarCoordinates().getId());
        return newCarUpdateDTO;
    }

}
