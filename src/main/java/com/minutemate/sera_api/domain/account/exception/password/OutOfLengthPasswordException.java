package com.minutemate.sera_api.domain.account.exception.password;

public class OutOfLengthPasswordException extends PasswordPolicyViolationException {
    public OutOfLengthPasswordException(String password) {
        super("비밀번호는 6자이상 20자 이하여야합니다! - " + password);
    }
}
