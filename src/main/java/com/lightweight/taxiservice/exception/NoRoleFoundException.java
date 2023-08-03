package com.lightweight.taxiservice.exception;

public class NoRoleFoundException extends RuntimeException{
    public NoRoleFoundException(String message) {
        super(message);
    }

    public NoRoleFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoRoleFoundException(Throwable cause) {
        super(cause);
    }
}
