package com.minutemate.sera_api.domain.account.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@RequiredArgsConstructor
public class StandardUserDetails implements UserDetails {
    private final Collection<? extends GrantedAuthority> authorities;
    private final String email;
    private final String password;
    private final String nickname;
    private final boolean emailVerified;
    private final boolean locked;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() { return false; }
    @Override
    public boolean isAccountNonLocked() { return !locked; }
    @Override
    public boolean isCredentialsNonExpired() { return false; }
    @Override
    public boolean isEnabled() { return emailVerified && !locked; }
}
