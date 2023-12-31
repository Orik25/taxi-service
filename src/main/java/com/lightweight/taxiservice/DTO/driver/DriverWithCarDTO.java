package com.lightweight.taxiservice.DTO.driver;

import com.lightweight.taxiservice.DTO.car.CarForNewDriverDTO;
import com.lightweight.taxiservice.validation.email.DriverUniqueEmail;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;

public class DriverWithCarDTO {
    @Pattern(message = "Must contain only letters, first must be uppercase",
            regexp = "^[A-Z][a-z]+$")
    private String firstName;
    @Pattern(message = "Must contain only letters, first must be uppercase",
            regexp = "^[A-Z][a-z]+$")
    private String lastName;
    @Pattern(message = "Unsupported type of number",
            regexp = "^(\\+\\d{1,3})?[-.\\s]?\\(?(\\d{3})\\)?[-.\\s]?(\\d{3})[-.\\s]?(\\d{4})$")
    private String phone;
    @Email
    @DriverUniqueEmail
    private String email;
    @Valid
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
