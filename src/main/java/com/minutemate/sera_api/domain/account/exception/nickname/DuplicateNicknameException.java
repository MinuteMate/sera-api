package com.minutemate.sera_api.domain.account.exception.nickname;

public class DuplicateNicknameException extends NicknamePolicyViolationException {
    public DuplicateNicknameException(String nickname) {
        super("중복된 닉네임입니다! - " + nickname);
    }
}
