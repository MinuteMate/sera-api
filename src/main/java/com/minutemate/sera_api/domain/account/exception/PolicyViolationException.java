package com.minutemate.sera_api.domain.account.exception;

public class PolicyViolationException extends RuntimeException{
    public PolicyViolationException(String s) {
        super(s);
    }
}
