package com.lightweight.taxiservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table(name = "car_coordinates")
public class CarCoordinates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "latitude", precision = 9, scale = 6)
    private Double latitude;
    @Column(name = "longitude", precision = 9, scale = 6)
    private Double longitude;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "car_id")
    @JsonBackReference(value = "coordinatesCar")
    private Car car;

    public CarCoordinates() {
    }

    public CarCoordinates(Double latitude, Double longitude, Car car) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.car = car;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Car getCar() {
        return car;
    }

    public void setCarId(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "CarCoordinates{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", car=" + car +
                '}';
    }
}
