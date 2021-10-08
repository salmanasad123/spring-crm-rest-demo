package com.luv2code.springdemo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// global exception handler
@ControllerAdvice
public class CustomerRestExceptionHandler {

    // add exception handler for CustomerNotFoundException
    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> handleException(CustomerNotFoundException exception) {

        // create CustomerErrorResponse object
        CustomerErrorResponse customerErrorResponse = new CustomerErrorResponse();
        customerErrorResponse.setErrorMessage(exception.getMessage());
        customerErrorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
        customerErrorResponse.setTimeStamp(System.currentTimeMillis());

        // return response entity
        return new ResponseEntity<>(customerErrorResponse, HttpStatus.NOT_FOUND);

    }

    // add another handler to catch any type of exception (generic)
    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> handleAnyException(Exception exception) {

        // create CustomerErrorResponse object
        CustomerErrorResponse customerErrorResponse = new CustomerErrorResponse();
        customerErrorResponse.setErrorMessage(exception.getMessage());
        customerErrorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        customerErrorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(customerErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
