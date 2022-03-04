package com.minutemate.sera_api.domain.account.data.dto;

public record UserDto(
        Long id,
        String nickname,
        String email,
        String encodedPassword
) {
}
