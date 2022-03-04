package com.minutemate.sera_api.domain.account.data.request;

public record SignupRequest(
        String emailToken,
        String password,
        String nickname
) {
}
