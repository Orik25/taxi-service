package com.lightweight.taxiservice.DTO.driver;

import com.lightweight.taxiservice.DTO.car.CarForNewDriverDTO;

public class DriverWithCarDTO {
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private CarForNewDriverDTO car;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CarForNewDriverDTO getCar() {
        return car;
    }

    public void setCar(CarForNewDriverDTO car) {
        this.car = car;
    }
}
