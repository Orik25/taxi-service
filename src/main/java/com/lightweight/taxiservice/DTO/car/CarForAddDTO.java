package com.lightweight.taxiservice.DTO.car;

import com.lightweight.taxiservice.DTO.driver.DriverForUpdateCarDTO;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CarForAddDTO {

    @NotBlank(message = "Brand is mandatory")
    private String brand;

    @NotBlank(message = "Model is mandatory")
    private String model;

    @NotNull(message = "Year is mandatory")
    private Integer year;

    @NotBlank(message = "Category is mandatory")
    private String category;

    @NotNull(message = "Status is mandatory")
    private String status;

    @NotNull(message = "Capacity is mandatory")
    private Integer capacity;

    private DriverForUpdateCarDTO driver;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public DriverForUpdateCarDTO getDriver() {
        return driver;
    }

    public void setDriver(DriverForUpdateCarDTO driver) {
        this.driver = driver;
    }

}
