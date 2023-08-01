package com.lightweight.taxiservice.exception;

public class NoCarsFoundException extends RuntimeException {

    public NoCarsFoundException(String message) {
        super(message);
    }

    public NoCarsFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoCarsFoundException(Throwable cause) {
        super(cause);
    }
}
