package com.lightweight.taxiservice.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalRestExceptionHandler {

    @ExceptionHandler(NoDriverFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(NoDriverFoundException exception) {
        ErrorResponse driverErrorResponse = new ErrorResponse();
        driverErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        driverErrorResponse.setMessage(exception.getMessage());
        driverErrorResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(driverErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoDriversFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(NoDriversFoundException exception) {
        ErrorResponse driverErrorResponse = new ErrorResponse();
        driverErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        driverErrorResponse.setMessage(exception.getMessage());
        driverErrorResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(driverErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoCarFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(NoCarFoundException exception) {
        ErrorResponse car = new ErrorResponse();
        car.setStatus(HttpStatus.NOT_FOUND.value());
        car.setMessage(exception.getMessage());
        car.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(car, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoCarsFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(NoCarsFoundException exception) {
        ErrorResponse carErrorResponse = new ErrorResponse();
        carErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        carErrorResponse.setMessage(exception.getMessage());
        carErrorResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(carErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoAvailableCarsException.class)
    public ResponseEntity<ErrorResponse> handleException(NoAvailableCarsException exception) {
        ErrorResponse carErrorResponse = new ErrorResponse();
        carErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        carErrorResponse.setMessage(exception.getMessage());
        carErrorResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(carErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolation(DataIntegrityViolationException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
