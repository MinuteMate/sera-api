package com.minutemate.sera_api.domain.account.exception.password;

public class MalformedPasswordException extends PasswordPolicyViolationException {
    public MalformedPasswordException(String password) {
        super("비밀번호에는 영문과 숫자가 포함되어있어야합니다! - " + password);
    }
}
