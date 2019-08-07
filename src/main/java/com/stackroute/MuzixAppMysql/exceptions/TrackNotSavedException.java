package com.stackroute.MuzixAppMysql.exceptions;

public class TrackNotSavedException extends Exception{

    //default constructor
    public TrackNotSavedException(){}

    //constructor with string message parameter
    public TrackNotSavedException(String message) {
        super(message);
    }
}


