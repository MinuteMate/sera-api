package com.minutemate.sera_api.domain.account.service;

import com.minutemate.sera_api.domain.account.data.dto.LoginTokenDto;
import com.minutemate.sera_api.domain.account.data.dto.UserDto;
import com.minutemate.sera_api.domain.account.data.entity.UserEntity;
import com.minutemate.sera_api.domain.account.exception.password.WrongPasswordException;
import com.minutemate.sera_api.domain.account.policy.EmailPolicy;
import com.minutemate.sera_api.domain.account.policy.NicknamePolicy;
import com.minutemate.sera_api.domain.account.policy.PasswordPolicy;
import com.minutemate.sera_api.domain.account.repository.UserRepository;
import com.minutemate.sera_api.domain.account.tokenizer.LoginJwtTokenizer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final NicknamePolicy nicknamePolicy;
    private final EmailPolicy emailPolicy;
    private final PasswordPolicy passwordPolicy;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final LoginJwtTokenizer loginJwtTokenizer;

    @Override
    public UserDto signup(final String nickname, final String email, final String password) {
        nicknamePolicy.checkSignupPolicy(nickname);
        emailPolicy.checkSignupPolicy(email);
        passwordPolicy.checkSignupPolicy(password);

        final String encodedPassword = passwordEncoder.encode(password);
        final UserEntity entity = new UserEntity(nickname, email, encodedPassword);
        userRepository.save(entity);
        return entity.toDto();
    }

    @Override
    public LoginTokenDto login(final String email, final  String password) {
        emailPolicy.checkLoginPolicy(email);
        passwordPolicy.checkLoginPolicy(password);

        final UserEntity entity = userRepository.getByEmail(email);
        if(!passwordEncoder.matches(password, entity.getEncodedPassword())) throw new WrongPasswordException(password);
        return generateLoginToken(entity.getId());
    }

    @Override
    public LoginTokenDto refreshLogin(final String refreshToken) {
        return generateLoginToken(loginJwtTokenizer.getIdByToken(refreshToken));
    }

    private LoginTokenDto generateLoginToken(final Long id) {
        return new LoginTokenDto(
                loginJwtTokenizer.getLoginTokenById(id),
                loginJwtTokenizer.getRefreshTokenById(id)
        );
    }
}
