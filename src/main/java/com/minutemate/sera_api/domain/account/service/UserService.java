package com.minutemate.sera_api.domain.account.service;

import com.minutemate.sera_api.domain.account.data.dto.LoginTokenDto;
import com.minutemate.sera_api.domain.account.data.dto.UserDto;

public interface UserService {
    UserDto signup(String nickname, String email, String password);

    LoginTokenDto login(String email, String password);

    LoginTokenDto refreshLogin(String refreshToken);
}
