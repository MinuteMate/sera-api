package com.minutemate.sera_api.domain.account.controller;

import com.minutemate.sera_api.domain.account.data.dto.LoginTokenDto;
import com.minutemate.sera_api.domain.account.data.dto.LoginWithRefreshTokenRequest;
import com.minutemate.sera_api.domain.account.data.request.LoginRequest;
import com.minutemate.sera_api.domain.account.data.response.LoginResponse;
import com.minutemate.sera_api.domain.account.data.response.LoginWithRefreshTokenResponse;
import com.minutemate.sera_api.domain.account.service.UserService;
import com.minutemate.sera_api.global.data.response.ErrorResponse;
import com.minutemate.sera_api.global.data.type.ErrorCode;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account/login")
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody final LoginRequest request) {
        final LoginTokenDto token = userService.login(request.email(), request.password());
        return ResponseEntity.ok(new LoginResponse(token.loginToken(), token.refreshToken()));
    }

    @PostMapping("/login/refresh")
    public ResponseEntity<?> loginWithRefreshToken(@RequestBody final LoginWithRefreshTokenRequest request) {
        final LoginTokenDto token = userService.refreshLogin(request.refreshToken());
        return ResponseEntity.ok(new LoginWithRefreshTokenResponse(token.loginToken(), token.refreshToken()));
    }
}
