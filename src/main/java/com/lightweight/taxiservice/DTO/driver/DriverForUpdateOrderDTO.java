package com.lightweight.taxiservice.DTO.driver;

import com.lightweight.taxiservice.DTO.car.CarForUpdateDriverDTO;
import jakarta.validation.constraints.NotNull;

public class DriverForUpdateOrderDTO {
    @NotNull(message = "Driver is mandatory")
    private Long id;
    private String firstName;
    private String lastName;
    private CarForUpdateDriverDTO car;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public CarForUpdateDriverDTO getCar() {
        return car;
    }

    public void setCar(CarForUpdateDriverDTO car) {
        this.car = car;
    }
}
