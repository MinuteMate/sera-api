package com.minutemate.sera_api.domain.account.exception;

public class UnknownAuthorizeNumberException extends RuntimeException{
    public UnknownAuthorizeNumberException(String authorizeNumber) {
        super("인증번호를 찾을 수 없습니다! - " + authorizeNumber);
    }
}
