package com.minutemate.sera_api.domain.account.exception.password;

import com.minutemate.sera_api.domain.account.exception.PolicyViolationException;

public class PasswordPolicyViolationException extends PolicyViolationException {
    public PasswordPolicyViolationException(String s) {
        super(s);
    }
}
