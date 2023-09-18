package com.lightweight.taxiservice.DTO.order;


import com.lightweight.taxiservice.DTO.car.CarForUpdateDriverDTO;
import com.lightweight.taxiservice.DTO.coordinates.ArrivalCoordinatesDTO;
import com.lightweight.taxiservice.DTO.coordinates.DispatchCoordinatesDTO;
import com.lightweight.taxiservice.DTO.driver.DriverForUpdateCarDTO;
import com.lightweight.taxiservice.DTO.driver.DriverForUpdateOrderDTO;
import com.lightweight.taxiservice.DTO.user.UserForUpdateOrderDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class OrderForUpdateDTO {
    private Long id;

    @Size(max = 250,message = "Size of comment must be less than 250 characters")
    private String comment;

    private String status;
    @Valid
    private UserForUpdateOrderDTO user;
    @NotNull
    private DriverForUpdateOrderDTO driver;

    @Valid
    private ArrivalCoordinatesDTO arrivalCoordinates;

    @Valid
    private DispatchCoordinatesDTO dispatchCoordinates;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public ArrivalCoordinatesDTO getArrivalCoordinates() {
        return arrivalCoordinates;
    }

    public void setArrivalCoordinates(ArrivalCoordinatesDTO arrivalCoordinates) {
        this.arrivalCoordinates = arrivalCoordinates;
    }

    public DispatchCoordinatesDTO getDispatchCoordinates() {
        return dispatchCoordinates;
    }

    public void setDispatchCoordinates(DispatchCoordinatesDTO dispatchCoordinates) {
        this.dispatchCoordinates = dispatchCoordinates;
    }
}
