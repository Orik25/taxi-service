package com.lightweight.taxiservice.exception;

public class NoDriversFoundException extends RuntimeException {

    public NoDriversFoundException(String message) {
        super(message);
    }

    public NoDriversFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoDriversFoundException(Throwable cause) {
        super(cause);
    }
}
