package com.lightweight.taxiservice.DTO.car;

import com.lightweight.taxiservice.DTO.driver.DriverForUpdateCarDTO;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.NumberFormat;

public class CarUpdateDTO {
    private Long id;

    @NotBlank(message = "Brand is mandatory")
    private String brand;

    @NotBlank(message = "Model is mandatory")
    private String model;


    private Integer year;

    @NotBlank(message = "Category is mandatory")
    private String category;

    @NotBlank(message = "Status is mandatory")
    private String status;



    private Integer capacity;

    private DriverForUpdateCarDTO driver;

    private Long coordinatesId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public DriverForUpdateCarDTO getDriver() {
        return driver;
    }

    public void setDriver(DriverForUpdateCarDTO driver) {
        this.driver = driver;
    }

    public Long getCoordinatesId() {
        return coordinatesId;
    }

    public void setCoordinatesId(Long coordinatesId) {
        this.coordinatesId = coordinatesId;
    }
}
