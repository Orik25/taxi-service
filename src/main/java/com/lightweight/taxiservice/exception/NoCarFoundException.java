package com.lightweight.taxiservice.exception;

public class NoCarFoundException extends RuntimeException {

    public NoCarFoundException(String message) {
        super(message);
    }

    public NoCarFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoCarFoundException(Throwable cause) {
        super(cause);
    }
}
