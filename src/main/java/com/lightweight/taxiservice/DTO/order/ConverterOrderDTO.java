package com.lightweight.taxiservice.DTO.order;

import com.lightweight.taxiservice.DTO.order.CreatedOrderDTO;
import com.lightweight.taxiservice.entity.*;
import com.lightweight.taxiservice.service.interfaces.DriverService;
import com.lightweight.taxiservice.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConverterOrderDTO {
    private UserService userService;
    private DriverService driverService;

    @Autowired
    public ConverterOrderDTO(UserService userService, DriverService driverService) {
        this.userService = userService;
        this.driverService = driverService;
    }

    public Order convertToEntity(CreatedOrderDTO createdOrderDTO){
        Order newOrder = new Order();
        newOrder.setComment(createdOrderDTO.getComment());
        ArrivalCoordinates arrivalCoordinates = new ArrivalCoordinates(
                createdOrderDTO.getArrivalCoordinates().getLatitude(),
                createdOrderDTO.getArrivalCoordinates().getLongitude(),
                newOrder
        );
        newOrder.setArrivalCoordinates(arrivalCoordinates);
        DispatchCoordinates dispatchCoordinates = new DispatchCoordinates(
                createdOrderDTO.getDispatchCoordinates().getLatitude(),
                createdOrderDTO.getDispatchCoordinates().getLongitude(),
                newOrder
        );
        newOrder.setDispatchCoordinates(dispatchCoordinates);
        Driver driver = driverService.findById(createdOrderDTO.getDriverId());
        User user = userService.findById(createdOrderDTO.getUserId());
        newOrder.setDriver(driver);
        newOrder.setUser(user);
        driver.getOrders().add(newOrder);
        user.getOrders().add(newOrder);

        System.out.println(createdOrderDTO.getUserId());
        return newOrder;
    }
}
