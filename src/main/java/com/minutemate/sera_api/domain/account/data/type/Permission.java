package com.minutemate.sera_api.domain.account.data.type;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public enum Permission implements GrantedAuthority {
    USER("user");

    private final String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
