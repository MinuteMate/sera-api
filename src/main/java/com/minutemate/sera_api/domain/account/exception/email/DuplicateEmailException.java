package com.minutemate.sera_api.domain.account.exception.email;

public class DuplicateEmailException extends EmailPolicyViolationException {
    public DuplicateEmailException(String email) {
        super("중복된 이메일입니다! - " + email);
    }
}
