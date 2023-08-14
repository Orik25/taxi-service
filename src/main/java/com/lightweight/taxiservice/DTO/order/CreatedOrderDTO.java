package com.lightweight.taxiservice.DTO.order;

public class CreatedOrderDTO {
    private String comment;
    private CoordinateDTO arrivalCoordinates;
    private CoordinateDTO dispatchCoordinates;

    private Long userId;

    private Long driverId;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public CoordinateDTO getArrivalCoordinates() {
        return arrivalCoordinates;
    }

    public void setArrivalCoordinates(CoordinateDTO arrivalCoordinates) {
        this.arrivalCoordinates = arrivalCoordinates;
    }

    public CoordinateDTO getDispatchCoordinates() {
        return dispatchCoordinates;
    }

    public void setDispatchCoordinates(CoordinateDTO dispatchCoordinates) {
        this.dispatchCoordinates = dispatchCoordinates;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }
}
