package com.webrise.assignment.domain.exseption;

public class SamePasswordException extends RuntimeException {
    public SamePasswordException(String message){
        super(message);
    }
}
