package com.lightweight.taxiservice.exception;

public class NoRolesFoundException extends RuntimeException{
    public NoRolesFoundException(String message) {
        super(message);
    }

    public NoRolesFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoRolesFoundException(Throwable cause) {
        super(cause);
    }
}
