package com.minutemate.sera_api.domain.account.tokenizer;

import com.minutemate.sera_api.domain.account.config.LoginJwtProperty;
import com.minutemate.sera_api.global.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class LoginJwtTokenizer {
    private final LoginJwtProperty loginJwtProperty;

    public String getLoginTokenById(Long id) {
        return getTokenByIdAndExpiredSeconds(id, loginJwtProperty.getLoginTokenExpiredSeconds());
    }

    public String getRefreshTokenById(Long id) {
        return getTokenByIdAndExpiredSeconds(id, loginJwtProperty.getRefreshTokenExpiredSeconds());
    }

    public String getTokenByIdAndExpiredSeconds(Long id, Long expiredSeconds) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", id);
        return JwtUtil.jwt(loginJwtProperty.getSecret(), expiredSeconds, claims);
    }

    public Long getIdByToken(String token) {
        return JwtUtil.parse(loginJwtProperty.getSecret(), token).get("id", Long.class);
    }
}
