package com.lightweight.taxiservice.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.lightweight.taxiservice.constant.OrderStatus;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "drivers")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String phone;

    @Column
    private String email;


    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST},
            mappedBy = "driver")
    @JsonManagedReference(value = "driverCar")
    private Car car;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "orderDriver")
    private List<Order> orders;

    public Driver() {
    }

    public Driver(String firstName, String lastName, String phone, String email, List<Order> orders) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.orders = orders;
    }

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

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Order getActiveOrder() {
        for (Order order : orders) {
            if(order.getStatus().equals(OrderStatus.ACTIVE.getStatus())){
                return order;
            }
        }
        throw new NullPointerException("Implement!!!(Entity Driver)");
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", car=" + car +
                '}';
    }
}