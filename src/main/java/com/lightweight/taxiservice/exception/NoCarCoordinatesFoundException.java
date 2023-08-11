package com.lightweight.taxiservice.exception;

public class NoCarCoordinatesFoundException extends RuntimeException {

    public NoCarCoordinatesFoundException(String message) {
        super(message);
    }

    public NoCarCoordinatesFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoCarCoordinatesFoundException(Throwable cause) {
        super(cause);
    }
}
