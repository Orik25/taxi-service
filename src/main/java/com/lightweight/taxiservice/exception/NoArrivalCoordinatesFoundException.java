package com.lightweight.taxiservice.exception;

public class NoArrivalCoordinatesFoundException extends RuntimeException {
    public NoArrivalCoordinatesFoundException(String message) {
        super(message);
    }

    public NoArrivalCoordinatesFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoArrivalCoordinatesFoundException(Throwable cause) {
        super(cause);
    }
}
