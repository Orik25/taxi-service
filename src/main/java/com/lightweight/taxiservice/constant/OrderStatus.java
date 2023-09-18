package com.lightweight.taxiservice.constant;

public enum OrderStatus {
    ACTIVE("active"),ARCHIVED("archived");

    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "OrderStatus{" +
                "status='" + status + '\'' +
                '}';
    }
}
