package com.lightweight.taxiservice.exception;

public class NoOrderFoundException extends RuntimeException {

    public NoOrderFoundException(String message) {
        super(message);
    }

    public NoOrderFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoOrderFoundException(Throwable cause) {
        super(cause);
    }
}
