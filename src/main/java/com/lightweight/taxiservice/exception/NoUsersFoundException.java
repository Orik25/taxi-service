package com.lightweight.taxiservice.exception;

public class NoUsersFoundException extends RuntimeException{
    public NoUsersFoundException(String message) {
        super(message);
    }

    public NoUsersFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoUsersFoundException(Throwable cause) {
        super(cause);
    }
}
