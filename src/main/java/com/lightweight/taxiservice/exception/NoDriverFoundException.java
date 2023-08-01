package com.lightweight.taxiservice.exception;

public class NoDriverFoundException extends RuntimeException {

    public NoDriverFoundException(String message) {
        super(message);
    }

    public NoDriverFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoDriverFoundException(Throwable cause) {
        super(cause);
    }
}
