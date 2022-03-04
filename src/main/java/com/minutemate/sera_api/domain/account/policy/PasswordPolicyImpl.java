package com.minutemate.sera_api.domain.account.policy;

import com.minutemate.sera_api.domain.account.exception.password.MalformedPasswordException;
import com.minutemate.sera_api.domain.account.exception.password.OutOfLengthPasswordException;
import org.springframework.stereotype.Component;

@Component
public class PasswordPolicyImpl implements PasswordPolicy {
    @Override
    public void checkSignupPolicy(String password) {
        if(password.length() < 6 || password.length() > 20) throw new OutOfLengthPasswordException(password);
        if(!password.matches("^[a-zA-Z0-9]*$")) throw new MalformedPasswordException(password);
    }

    @Override
    public void checkLoginPolicy(String password) {
        if(password.length() < 6 || password.length() > 20) throw new OutOfLengthPasswordException(password);
        if(!password.matches("^[a-zA-Z0-9]*$")) throw new MalformedPasswordException(password);
    }
}
