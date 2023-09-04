package com.lightweight.taxiservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity

@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String brand;

    @Column
    private String model;

    @Column
    private int year;

    @Column
    private String category;

    @Column
    private String status;

    @Column
    private int capacity;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "driver_id")
    @JsonBackReference(value = "driverCar")
    private Driver driver;

    @OneToOne(cascade = {CascadeType.ALL}
            ,mappedBy = "car")
    @JsonManagedReference(value = "coordinatesCar")
    private CarCoordinates carCoordinates;

    public Car() {
    }

    public Car(String brand, String model, int year, String category, String status, int capacity) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.category = category;
        this.status = status;
        this.capacity = capacity;
    }

    public Car(CarCoordinates carCoordinates) {
        this.carCoordinates = carCoordinates;
    }

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

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public CarCoordinates getCarCoordinates() {
        return carCoordinates;
    }

    public void setCarCoordinates(CarCoordinates carCoordinates) {
        this.carCoordinates = carCoordinates;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", category='" + category + '\'' +
                ", status='" + status + '\'' +
                ", capacity=" + capacity +
                ", driver=" + driver +
                ", carCoordinates=" + carCoordinates +
                '}';
    }
}
