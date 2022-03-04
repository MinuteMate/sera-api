package com.minutemate.sera_api.domain.account;

import com.minutemate.sera_api.domain.account.config.AuthorizeJwtProperty;
import com.minutemate.sera_api.global.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AuthorizeJwtTokenizer {
    private final AuthorizeJwtProperty authorizeJwtProperty;

    public String getTokenByEmail(String email) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", email);
        return JwtUtil.jwt(authorizeJwtProperty.getSecret(), authorizeJwtProperty.getExpiredSeconds(), claims);
    }
}
