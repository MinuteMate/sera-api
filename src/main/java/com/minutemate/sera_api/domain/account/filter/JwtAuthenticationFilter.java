package com.minutemate.sera_api.domain.account.filter;

import com.minutemate.sera_api.domain.account.config.AuthorizeJwtProperty;
import com.minutemate.sera_api.domain.account.data.entity.UserEntity;
import com.minutemate.sera_api.domain.account.repository.UserRepository;
import com.minutemate.sera_api.domain.account.tokenizer.LoginJwtTokenizer;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {
    private final LoginJwtTokenizer loginJwtTokenizer;
    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        String token = ((HttpServletRequest)request).getHeader("X-Login-Token");
        try {
            final UserDetails userDetails = getUserDetailsByToken(token);
            final Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (JwtException e) {}
        chain.doFilter(request, response);
    }

    private UserDetails getUserDetailsByToken(String token) {
        return userDetailsService.loadUserByUsername(userRepository.getById(loginJwtTokenizer.getIdByToken(token)).getEmail());
    }
}
