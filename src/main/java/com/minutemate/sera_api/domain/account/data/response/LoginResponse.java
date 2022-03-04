package com.minutemate.sera_api.domain.account.data.response;

public record LoginResponse(
        String loginToken,
        String refreshToken
) {
}
