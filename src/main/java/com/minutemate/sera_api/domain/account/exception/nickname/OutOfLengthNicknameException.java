package com.minutemate.sera_api.domain.account.exception.nickname;

public class OutOfLengthNicknameException extends NicknamePolicyViolationException {
    public OutOfLengthNicknameException(String nickname) {
        super("닉네임은 1자이상 10자 이하여야합니다! - " + nickname);
    }
}
