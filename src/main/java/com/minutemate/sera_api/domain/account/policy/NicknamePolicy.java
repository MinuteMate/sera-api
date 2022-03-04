package com.minutemate.sera_api.domain.account.policy;

@FunctionalInterface
public interface NicknamePolicy {
    void checkSignupPolicy(String nickname);
}
