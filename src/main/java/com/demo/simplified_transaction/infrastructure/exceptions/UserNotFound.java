package com.demo.simplified_transaction.infrastructure.exceptions;

public class UserNotFound extends RuntimeException{

    public UserNotFound(String message) {
        super(message);
    }
}
