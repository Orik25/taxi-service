package com.lightweight.taxiservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.lightweight.taxiservice.validation.email.UniqueEmail;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "Not correct email")
    @NotBlank(message = "Email is mandatory")
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Pattern(message = "Must contain only letters, first must be uppercase",
            regexp = "^[A-Z][a-z]+$")
    @Column(name = "first_name")
    private String firstName;

    @Pattern(message = "Must contain only letters, first must be uppercase",
            regexp = "^[A-Z][a-z]+$")
    @Column(name = "last_name")
    private String lastName;

    @Pattern(message = "Must contain at least 1 capital letter, at least 1 number, at least 8 characters",
            regexp = "^(?=.*[A-Z])(?=.*\\d).{8,}$")
    @Column(name = "password")
    private String password;

    @Pattern(message = "Unsupported type of number",
            regexp = "^(\\+\\d{1,3})?[-.\\s]?\\(?(\\d{3})\\)?[-.\\s]?(\\d{3})[-.\\s]?(\\d{4})$")
    @Column(name = "phone", nullable = false)
    private String phone;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    @JsonBackReference(value = "userRole")
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "orderUser")
    private List<Order> orders;

    public User() {
    }

    public User(String email, String firstName, String lastName, String password,
                String phone, Role role, List<Order> orders) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.phone = phone;
        this.role = role;
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
