package com.minutemate.sera_api.domain.account.policy;

public interface EmailPolicy {
    void checkSignupPolicy(String email);
    void checkLoginPolicy(String email);
}
