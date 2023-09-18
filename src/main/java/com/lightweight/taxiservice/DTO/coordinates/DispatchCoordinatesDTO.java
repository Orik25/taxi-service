package com.lightweight.taxiservice.DTO.coordinates;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class DispatchCoordinatesDTO {
    private Long id;

    @NotNull(message = "Latitude is mandatory")
    @DecimalMin(value = "-90.0", inclusive = true, message = "Latitude must be greater -90.0")
    @DecimalMax(value = "90.0", inclusive = true, message = "Latitude must be less 90.0")
    private Double latitude;

    @NotNull(message = "Longitude is mandatory")
    @DecimalMin(value = "-180.0", inclusive = true, message = "Longitude must be greater -180.0")
    @DecimalMax(value = "180.0", inclusive = true, message = "Longitude must be less 180.0")
    private Double longitude;

    public void setId(Long id) {
        this.id = id;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
