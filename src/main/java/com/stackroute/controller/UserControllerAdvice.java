package com.stackroute.controller;

import com.stackroute.exceptions.UserAlreadyExistsException;
import com.stackroute.exceptions.UserNotFoundException;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.util.Optional;

@ControllerAdvice(basePackages = "com.stackroute")
public class UserControllerAdvice {

//    @ExceptionHandler(value = Exception.class)
//    public ResponseEntity<String> exceptionHandler(Exception e) {
//        return new ResponseEntity<>("Error thrown globally : " + e.getMessage(), HttpStatus.CONFLICT);

    private ResponseEntity<VndErrors> error(final Exception exception, final HttpStatus httpStatus, final String logRef) {
        final String message = Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());
        return new ResponseEntity<>(new VndErrors(logRef, message), httpStatus);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<VndErrors> notFoundException(final UserNotFoundException e) {
        return error(e, HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<VndErrors> alreadyExistsException(final UserAlreadyExistsException e) {
        return error(e, HttpStatus.CONFLICT, e.getLocalizedMessage());
    }
}
