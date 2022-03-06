package com.minutemate.sera_api.domain.account.service;

import com.minutemate.sera_api.domain.account.data.StandardUserDetails;
import com.minutemate.sera_api.domain.account.data.dto.UserDto;
import com.minutemate.sera_api.domain.account.data.type.Permission;
import com.minutemate.sera_api.domain.account.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StandardUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final UserDto dto = userRepository.getByEmail(email).toDto();
        return new StandardUserDetails(
                List.of(Permission.USER),
                dto.email(), dto.encodedPassword(), dto.nickname(),
                true, false);
    }
}
