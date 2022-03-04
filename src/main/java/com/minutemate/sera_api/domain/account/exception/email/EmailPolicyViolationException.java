package com.minutemate.sera_api.domain.account.exception.email;

import com.minutemate.sera_api.domain.account.exception.PolicyViolationException;

public class EmailPolicyViolationException extends PolicyViolationException {
    public EmailPolicyViolationException(String s) {
        super(s);
    }
}
