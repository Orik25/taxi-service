package com.lightweight.taxiservice.DTO.driver;

import com.lightweight.taxiservice.DTO.car.CarForUpdateDriverDTO;
import com.lightweight.taxiservice.validation.email.DriverUpdateUniqueEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

@DriverUpdateUniqueEmail
public class DriverUpdateDTO {
    private Long id;
    private String globalError;

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
    private String email;
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

    public CarForUpdateDriverDTO getCar() {
        return car;
    }

    public void setCar(CarForUpdateDriverDTO car) {
        this.car = car;
    }

    public String getGlobalError() {
        return globalError;
    }

    public void setGlobalError(String globalError) {
        this.globalError = globalError;
    }
}
