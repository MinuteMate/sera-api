package com.minutemate.sera_api.domain.account.exception.nickname;

import com.minutemate.sera_api.domain.account.exception.PolicyViolationException;

public class NicknamePolicyViolationException extends PolicyViolationException {
    public NicknamePolicyViolationException(String s) {
        super(s);
    }
}
