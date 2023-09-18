package com.lightweight.taxiservice.constant;

public enum CarStatus {
    ON_TRIP("on_trip"),INACTIVE("inactive"),AVAILABLE("available");

    private final String status;

    public String getStatus() {
        return status;
    }

    CarStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CarStatus{" +
                "status='" + status + '\'' +
                '}';
    }
}
