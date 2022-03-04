package com.minutemate.sera_api.domain.account.data.request;

public record LoginRequest(
        String email,
        String password
) {}
