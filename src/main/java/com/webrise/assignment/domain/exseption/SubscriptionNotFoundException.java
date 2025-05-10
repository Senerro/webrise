package com.webrise.assignment.domain.exseption;

public class SubscriptionNotFoundException extends RuntimeException {
    public SubscriptionNotFoundException(String message){
        super(message);
    }
}
