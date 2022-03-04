package com.minutemate.sera_api.domain.account.data.dto;

public record LoginTokenDto(
        String loginToken,
        String refreshToken
) {
}
