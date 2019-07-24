package com.stackroute.exceptions;

public class UserNotFoundException extends Throwable {

    private String message;

    public UserNotFoundException(){}

    public UserNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
