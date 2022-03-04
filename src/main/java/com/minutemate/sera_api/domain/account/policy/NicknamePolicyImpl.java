package com.minutemate.sera_api.domain.account.policy;

import com.minutemate.sera_api.domain.account.exception.nickname.DuplicateNicknameException;
import com.minutemate.sera_api.domain.account.exception.nickname.OutOfLengthNicknameException;
import com.minutemate.sera_api.domain.account.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NicknamePolicyImpl implements NicknamePolicy{
    private final UserRepository userRepository;

    @Override
    public void checkSignupPolicy(String nickname) {
        if(nickname.length() > 10) throw new OutOfLengthNicknameException(nickname);
        if(userRepository.existsByNickname(nickname)) throw new DuplicateNicknameException(nickname);
    }
}
