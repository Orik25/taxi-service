package com.lightweight.taxiservice.exception;

public class NoListOfCarCoordinatesFoundException extends RuntimeException{
    public NoListOfCarCoordinatesFoundException(String message) {
        super(message);
    }

    public NoListOfCarCoordinatesFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoListOfCarCoordinatesFoundException(Throwable cause) {
        super(cause);
    }
}
