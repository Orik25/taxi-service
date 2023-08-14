package com.lightweight.taxiservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String comment;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "orderUser")
    private User user;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "driver_id")
    @JsonBackReference(value = "driverUser")
    private Driver driver;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
    @JsonManagedReference(value = "arrivalOrder")
    private ArrivalCoordinates arrivalCoordinates;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
    @JsonManagedReference(value = "dispatchOrder")
    private DispatchCoordinates dispatchCoordinates;

    public Order() {
    }

    public Order(String comment, User user, Driver driver,
                 ArrivalCoordinates arrivalCoordinates, DispatchCoordinates dispatchCoordinates) {
        this.comment = comment;
        this.user = user;
        this.driver = driver;
        this.arrivalCoordinates = arrivalCoordinates;
        this.dispatchCoordinates = dispatchCoordinates;
    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public ArrivalCoordinates getArrivalCoordinates() {
        return arrivalCoordinates;
    }

    public void setArrivalCoordinates(ArrivalCoordinates arrivalCoordinates) {
        this.arrivalCoordinates = arrivalCoordinates;
    }

    public DispatchCoordinates getDispatchCoordinates() {
        return dispatchCoordinates;
    }

    public void setDispatchCoordinates(DispatchCoordinates dispatchCoordinates) {
        this.dispatchCoordinates = dispatchCoordinates;
    }
}
