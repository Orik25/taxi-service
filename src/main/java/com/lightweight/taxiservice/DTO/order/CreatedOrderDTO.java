package com.lightweight.taxiservice.DTO.order;

import com.lightweight.taxiservice.DTO.coordinates.CoordinateForNewOrderDTO;
import com.lightweight.taxiservice.DTO.driver.DriverForUpdateCarDTO;
import com.lightweight.taxiservice.DTO.driver.DriverForUpdateOrderDTO;
import com.lightweight.taxiservice.DTO.user.UserForUpdateOrderDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreatedOrderDTO {

    @Size(max = 250, message = "Size of comment must be less than 250 characters")
    private String comment;

    @Valid
    private CoordinateForNewOrderDTO arrivalCoordinates;

    @Valid
    private CoordinateForNewOrderDTO dispatchCoordinates;

    @Valid
    private UserForUpdateOrderDTO user;

    @Valid
    private DriverForUpdateOrderDTO driver;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public CoordinateForNewOrderDTO getArrivalCoordinates() {
        return arrivalCoordinates;
    }

    public void setArrivalCoordinates(CoordinateForNewOrderDTO arrivalCoordinates) {
        this.arrivalCoordinates = arrivalCoordinates;
    }

    public CoordinateForNewOrderDTO getDispatchCoordinates() {
        return dispatchCoordinates;
    }

    public void setDispatchCoordinates(CoordinateForNewOrderDTO dispatchCoordinates) {
        this.dispatchCoordinates = dispatchCoordinates;
    }

    public UserForUpdateOrderDTO getUser() {
        return user;
    }

    public void setUser(UserForUpdateOrderDTO user) {
        this.user = user;
    }

    public DriverForUpdateOrderDTO getDriver() {
        return driver;
    }

    public void setDriver(DriverForUpdateOrderDTO driver) {
        this.driver = driver;
    }
}
