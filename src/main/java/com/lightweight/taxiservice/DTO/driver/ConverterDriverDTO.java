package com.lightweight.taxiservice.DTO.driver;

import com.lightweight.taxiservice.entity.Car;
import com.lightweight.taxiservice.entity.CarCoordinates;
import com.lightweight.taxiservice.entity.Driver;
import com.lightweight.taxiservice.entity.Order;
import com.lightweight.taxiservice.service.interfaces.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConverterDriverDTO {
    private CarService carService;

    @Autowired
    public ConverterDriverDTO(CarService carService) {
        this.carService = carService;
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

        Car car = carService.findById(driverWithOutCar.getCarId());

        newDriver.setCar(car);
        car.setDriver(newDriver);

        List<Order> newListOrder = new ArrayList<>();
        newDriver.setOrders(newListOrder);

        return newDriver;
    }
}
