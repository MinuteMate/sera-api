package com.minutemate.sera_api.domain.account.data.response;

public record LoginWithRefreshTokenResponse(
        String loginToken,
        String refreshToken) {
}
