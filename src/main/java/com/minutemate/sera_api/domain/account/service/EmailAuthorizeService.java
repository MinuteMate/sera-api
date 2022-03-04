package com.minutemate.sera_api.domain.account.service;

import org.springframework.stereotype.Service;

@Service
public interface EmailAuthorizeService {
    String generateAuthorizeNumber();

    void addAuthorizeNumber(String email, String authorizeNumber);

    void sendAuthorizeNumber(String email, String authorizeNumber);

    String getEmailByNumber(String authorizeNumber);

    String getTokenByEmail(String email);
}
