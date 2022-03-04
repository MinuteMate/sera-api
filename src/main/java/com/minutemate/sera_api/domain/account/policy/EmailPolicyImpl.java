package com.minutemate.sera_api.domain.account.policy;


import com.minutemate.sera_api.domain.account.exception.email.DuplicateEmailException;
import com.minutemate.sera_api.domain.account.exception.email.UnknownEmailException;
import com.minutemate.sera_api.domain.account.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailPolicyImpl implements EmailPolicy{
    private final UserRepository userRepository;

    @Override
    public void checkSignupPolicy(String email) {
        if(userRepository.existsByEmail(email)) throw new DuplicateEmailException(email);
    }

    @Override
    public void checkLoginPolicy(String email) {
        if(!userRepository.existsByEmail(email)) throw new UnknownEmailException(email);
    }
}
