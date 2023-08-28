package com.lightweight.taxiservice.DTO.user;

import com.lightweight.taxiservice.validation.email.UniqueEmail;
import com.lightweight.taxiservice.validation.email.UpdateUniqueEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.HashMap;
import java.util.Map;

@UpdateUniqueEmail
public class UserUpdateProfileDTO {
    private Long id;

    private String globalError;
    @Email(message = "Not correct email")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @Pattern(message = "Must contain only letters, first must be uppercase",
            regexp = "^[A-Z][a-z]+$")
    private String firstName;

    @Pattern(message = "Must contain only letters, first must be uppercase",
            regexp = "^[A-Z][a-z]+$")
    private String lastName;

    @NotBlank
    @Pattern(message = "Unsupported type of number",
            regexp = "^(\\+\\d{1,3})?[-.\\s]?\\(?(\\d{3})\\)?[-.\\s]?(\\d{3})[-.\\s]?(\\d{4})$")
    private String phone;

    @NotBlank
    private String roleName;

    public UserUpdateProfileDTO() {
    }

    public UserUpdateProfileDTO(Long id, String email, String firstName, String lastName, String phone, String roleName) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.roleName = roleName;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getGlobalError() {
        return globalError;
    }

    public void setGlobalError(String globalError) {
        this.globalError = globalError;
    }
}
