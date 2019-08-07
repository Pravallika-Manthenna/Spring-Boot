package com.stackroute.MuzixAppMysql.exceptions;

//track already exists exception extending exception
public class TrackAlreadyExistsException extends Exception {

    //default constructor
    public TrackAlreadyExistsException(){}

    //constructor with string message parameter
    public TrackAlreadyExistsException(String message) {
        super(message);
    }
}
