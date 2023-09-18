package com.lightweight.taxiservice.DTO.order;

import com.lightweight.taxiservice.DTO.car.CarForUpdateDriverDTO;
import com.lightweight.taxiservice.DTO.coordinates.ArrivalCoordinatesDTO;
import com.lightweight.taxiservice.DTO.coordinates.DispatchCoordinatesDTO;
import com.lightweight.taxiservice.DTO.driver.DriverForUpdateCarDTO;
import com.lightweight.taxiservice.DTO.driver.DriverForUpdateOrderDTO;
import com.lightweight.taxiservice.DTO.order.CreatedOrderDTO;
import com.lightweight.taxiservice.DTO.user.UserForUpdateOrderDTO;
import com.lightweight.taxiservice.constant.CarStatus;
import com.lightweight.taxiservice.constant.OrderStatus;
import com.lightweight.taxiservice.entity.*;
import com.lightweight.taxiservice.service.interfaces.ArrivalCoordinatesService;
import com.lightweight.taxiservice.service.interfaces.DispatchCoordinatesService;
import com.lightweight.taxiservice.service.interfaces.DriverService;
import com.lightweight.taxiservice.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConverterOrderDTO {
    private UserService userService;
    private DriverService driverService;

    private ArrivalCoordinatesService arrivalCoordinatesService;
    private DispatchCoordinatesService dispatchCoordinatesService;

    @Autowired
    public ConverterOrderDTO(UserService userService,
                             DriverService driverService,
                             ArrivalCoordinatesService arrivalCoordinatesService,
                             DispatchCoordinatesService dispatchCoordinatesService) {
        this.userService = userService;
        this.driverService = driverService;
        this.arrivalCoordinatesService = arrivalCoordinatesService;
        this.dispatchCoordinatesService = dispatchCoordinatesService;
    }


    public Order convertToEntity(CreatedOrderDTO createdOrderDTO){
        Order newOrder = new Order();
        newOrder.setComment(createdOrderDTO.getComment());
        newOrder.setStatus(OrderStatus.ACTIVE.getStatus());
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
        Driver driver = driverService.findById(createdOrderDTO.getDriver().getId());
        driver.getCar().setStatus(CarStatus.ON_TRIP.getStatus());
        User user = userService.findById(createdOrderDTO.getUser().getId());
        newOrder.setDriver(driver);
        newOrder.setUser(user);
        driver.getOrders().add(newOrder);
        user.getOrders().add(newOrder);

        return newOrder;
    }
    public Order convertToEntity(OrderForUpdateDTO orderForUpdateDTO,Order order){

        order.setComment(orderForUpdateDTO.getComment());
        order.setStatus(orderForUpdateDTO.getStatus());
        order.getDriver().getCar().setStatus(CarStatus.AVAILABLE.getStatus());
        ArrivalCoordinates arrivalCoordinates = arrivalCoordinatesService.findById(orderForUpdateDTO.getArrivalCoordinates().getId());
        DispatchCoordinates dispatchCoordinates = dispatchCoordinatesService.findById(orderForUpdateDTO.getDispatchCoordinates().getId());

        arrivalCoordinates.setLatitude(orderForUpdateDTO.getArrivalCoordinates().getLatitude());
        arrivalCoordinates.setLongitude(orderForUpdateDTO.getArrivalCoordinates().getLongitude());

        dispatchCoordinates.setLatitude(orderForUpdateDTO.getDispatchCoordinates().getLatitude());
        dispatchCoordinates.setLongitude(orderForUpdateDTO.getDispatchCoordinates().getLongitude());

        Driver driver = driverService.findById(orderForUpdateDTO.getDriver().getId());
        User user = userService.findById(orderForUpdateDTO.getUser().getId());

        driver.getCar().setStatus(CarStatus.ON_TRIP.getStatus());

        order.setDriver(driver);
        order.setUser(user);
        order.setArrivalCoordinates(arrivalCoordinates);
        order.setDispatchCoordinates(dispatchCoordinates);


        return order;
    }

    public OrderForUpdateDTO convertToDTO(Order order){
        OrderForUpdateDTO newOrder = new OrderForUpdateDTO();
        UserForUpdateOrderDTO user = new UserForUpdateOrderDTO();
        CarForUpdateDriverDTO car = new CarForUpdateDriverDTO();
        DriverForUpdateOrderDTO driver = new DriverForUpdateOrderDTO();
        ArrivalCoordinatesDTO arrivalCoordinates = new ArrivalCoordinatesDTO();
        DispatchCoordinatesDTO dispatchCoordinates = new DispatchCoordinatesDTO();

        newOrder.setId(order.getId());
        newOrder.setComment(order.getComment());
        newOrder.setStatus(order.getStatus());
        user.setId(order.getUser().getId());
        user.setFirstName(order.getUser().getFirstName());
        user.setLastName(order.getUser().getLastName());

        driver.setId(order.getDriver().getId());
        driver.setFirstName(order.getDriver().getFirstName());
        driver.setLastName(order.getDriver().getLastName());

        car.setId(order.getDriver().getCar().getId());
        car.setBrand(order.getDriver().getCar().getBrand());
        car.setModel(order.getDriver().getCar().getModel());

        arrivalCoordinates.setId(order.getArrivalCoordinates().getId());
        arrivalCoordinates.setLatitude(order.getArrivalCoordinates().getLatitude());
        arrivalCoordinates.setLongitude(order.getArrivalCoordinates().getLongitude());

        dispatchCoordinates.setId(order.getArrivalCoordinates().getId());
        dispatchCoordinates.setLatitude(order.getDispatchCoordinates().getLatitude());
        dispatchCoordinates.setLongitude(order.getDispatchCoordinates().getLongitude());

        driver.setCar(car);
        newOrder.setUser(user);
        newOrder.setDriver(driver);
        newOrder.setArrivalCoordinates(arrivalCoordinates);
        newOrder.setDispatchCoordinates(dispatchCoordinates);

        return newOrder;
    }
}
