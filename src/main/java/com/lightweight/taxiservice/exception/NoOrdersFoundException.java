package com.lightweight.taxiservice.exception;

public class NoOrdersFoundException extends RuntimeException {

    public NoOrdersFoundException(String message) {
        super(message);
    }

    public NoOrdersFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoOrdersFoundException(Throwable cause) {
        super(cause);
    }
}
