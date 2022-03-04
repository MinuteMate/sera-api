package com.minutemate.sera_api.domain.account.exception.email;

public class UnknownEmailException extends EmailPolicyViolationException {
    public UnknownEmailException(String email) {
        super("존재하지 않는 이메일입니다! - " + email);
    }
}
