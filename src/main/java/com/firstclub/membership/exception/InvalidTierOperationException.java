package com.firstclub.membership.exception;


public class InvalidTierOperationException extends RuntimeException {
    public InvalidTierOperationException(String message) {
        super(message);
    }
}

