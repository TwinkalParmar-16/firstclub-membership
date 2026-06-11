package com.firstclub.membership.exception;

public class SubscriptionAlreadyExistsException extends RuntimeException {
    public SubscriptionAlreadyExistsException(String userId) {
        super("User " + userId + " already has an active subscription. Cancel it first to re-subscribe.");
    }
}
