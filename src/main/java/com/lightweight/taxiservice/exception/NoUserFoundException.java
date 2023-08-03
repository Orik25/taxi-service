package com.lightweight.taxiservice.exception;

public class NoUserFoundException extends RuntimeException{
    public NoUserFoundException(String message) {
        super(message);
    }

    public NoUserFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoUserFoundException(Throwable cause) {
        super(cause);
    }
}
