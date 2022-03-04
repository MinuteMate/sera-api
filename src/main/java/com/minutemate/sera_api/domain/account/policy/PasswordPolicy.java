package com.minutemate.sera_api.domain.account.policy;

public interface PasswordPolicy {
    void checkSignupPolicy(String password);

    void checkLoginPolicy(String password);
}
