package com.minutemate.sera_api.domain.account.exception.password;

import com.minutemate.sera_api.domain.account.exception.PolicyViolationException;

public class WrongPasswordException extends PolicyViolationException {
    public WrongPasswordException(String password) {
        super("잘못된 비밀번호입니다! - " + password);
    }
}
