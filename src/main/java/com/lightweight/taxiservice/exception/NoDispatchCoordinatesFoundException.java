package com.lightweight.taxiservice.exception;

public class NoDispatchCoordinatesFoundException extends RuntimeException {
    public NoDispatchCoordinatesFoundException(String message) {
        super(message);
    }

    public NoDispatchCoordinatesFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoDispatchCoordinatesFoundException(Throwable cause) {
        super(cause);
    }
}
