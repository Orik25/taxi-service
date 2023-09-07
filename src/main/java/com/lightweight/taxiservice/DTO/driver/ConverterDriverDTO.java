package com.lightweight.taxiservice.DTO.driver;

import com.lightweight.taxiservice.DAO.CarRepository;
import com.lightweight.taxiservice.DTO.car.CarForUpdateDriverDTO;
import com.lightweight.taxiservice.entity.Car;
import com.lightweight.taxiservice.entity.CarCoordinates;
import com.lightweight.taxiservice.entity.Driver;
import com.lightweight.taxiservice.entity.Order;
import com.lightweight.taxiservice.exception.NoCarFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConverterDriverDTO {
    private CarRepository carRepository;

    @Autowired
    public ConverterDriverDTO(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Driver convertToEntity(DriverWithCarDTO driverWithCarDTO) {
        Driver newDriver = new Driver();
        newDriver.setFirstName(driverWithCarDTO.getFirstName());
        newDriver.setLastName(driverWithCarDTO.getLastName());
        newDriver.setPhone(driverWithCarDTO.getPhone());
        newDriver.setEmail(driverWithCarDTO.getEmail());

        Car newCar = new Car();
        newCar.setBrand(driverWithCarDTO.getCar().getBrand());
        newCar.setModel(driverWithCarDTO.getCar().getModel());
        newCar.setYear(driverWithCarDTO.getCar().getYear());
        newCar.setCategory(driverWithCarDTO.getCar().getCategory());
        newCar.setStatus("available");
        newCar.setCapacity(driverWithCarDTO.getCar().getCapacity());

        CarCoordinates newCarCoordinates = new CarCoordinates(0.0, 0.0, newCar);
        List<Order> newListOrder = new ArrayList<>();

        newDriver.setOrders(newListOrder);
        newCar.setCarCoordinates(newCarCoordinates);
        newDriver.setCar(newCar);
        newCar.setDriver(newDriver);


        return newDriver;
    }

    public Driver convertToEntity(DriverWithOutCarDTO driverWithOutCar) {
        Driver newDriver = new Driver();
        newDriver.setFirstName(driverWithOutCar.getFirstName());
        newDriver.setLastName(driverWithOutCar.getLastName());
        newDriver.setPhone(driverWithOutCar.getPhone());
        newDriver.setEmail(driverWithOutCar.getEmail());


        if (driverWithOutCar.getCarId() != null) {
            Car car = carRepository.findById(driverWithOutCar.getCarId()).orElseThrow(() ->
                    new NoCarFoundException("Car not found"));

            newDriver.setCar(car);
            car.setDriver(newDriver);
        } else {
            newDriver.setCar(null);
        }

        List<Order> newListOrder = new ArrayList<>();
        newDriver.setOrders(newListOrder);

        return newDriver;
    }

    public Driver convertToEntity(DriverUpdateDTO updateDriverDTO, Driver driver) {
        driver.setFirstName(updateDriverDTO.getFirstName());
        driver.setLastName(updateDriverDTO.getLastName());
        driver.setPhone(updateDriverDTO.getPhone());
        driver.setEmail(updateDriverDTO.getEmail());
        if (updateDriverDTO.getCar().getId() != null) {

            Car car = carRepository.findById(updateDriverDTO.getCar().getId()).orElseThrow(() ->
                    new NoCarFoundException("Car not found"));

            car.setDriver(driver);
            driver.setCar(car);
        } else {
            if (driver.getCar() != null)
                driver.getCar().setDriver(null);
        }

        return driver;
    }

    public List<DriverForUpdateCarDTO> convertToListDTOs(List<Driver> drivers) {
        List<DriverForUpdateCarDTO> driverForUpdateCarList = new ArrayList<>();
        for (Driver driver : drivers) {
            driverForUpdateCarList.add(convertToDTO(driver));
        }
        return driverForUpdateCarList;
    }

    private DriverForUpdateCarDTO convertToDTO(Driver driver) {
        DriverForUpdateCarDTO newDriver = new DriverForUpdateCarDTO();
        newDriver.setId(driver.getId());
        newDriver.setFirstName(driver.getFirstName());
        newDriver.setLastName(driver.getLastName());
        return newDriver;
    }

    public DriverUpdateDTO convertToDTOForUpdate(Driver driver) {
        DriverUpdateDTO newDriver = new DriverUpdateDTO();
        newDriver.setId(driver.getId());
        newDriver.setFirstName(driver.getFirstName());
        newDriver.setLastName(driver.getLastName());
        newDriver.setPhone(driver.getPhone());
        newDriver.setEmail(driver.getEmail());

        if (driver.getCar() != null) {
            CarForUpdateDriverDTO car = new CarForUpdateDriverDTO();
            car.setId(driver.getCar().getId());
            car.setBrand(driver.getCar().getBrand());
            car.setModel(driver.getCar().getModel());

            newDriver.setCar(car);
        } else {
            newDriver.setCar(null);
        }

        return newDriver;
    }
}
