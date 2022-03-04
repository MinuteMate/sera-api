package com.minutemate.sera_api.domain.account.controller;

import com.minutemate.sera_api.domain.account.tokenizer.AuthorizeJwtTokenizer;
import com.minutemate.sera_api.domain.account.data.dto.UserDto;
import com.minutemate.sera_api.domain.account.data.request.SignupRequest;
import com.minutemate.sera_api.domain.account.exception.email.EmailPolicyViolationException;
import com.minutemate.sera_api.domain.account.exception.nickname.NicknamePolicyViolationException;
import com.minutemate.sera_api.domain.account.exception.password.PasswordPolicyViolationException;
import com.minutemate.sera_api.domain.account.service.UserService;
import com.minutemate.sera_api.global.data.response.ErrorResponse;
import com.minutemate.sera_api.global.data.type.ErrorCode;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account/signup")
@RequiredArgsConstructor
public class SignupController {
    private final AuthorizeJwtTokenizer authorizeJwtTokenizer;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> signup(@RequestBody final SignupRequest signupRequest) {
        final String email = authorizeJwtTokenizer.getEmailByToken(signupRequest.emailToken());
        final UserDto user = userService.signup(signupRequest.nickname(), email, signupRequest.password());
        return ResponseEntity.ok().body(user);
    }

    @ExceptionHandler(NicknamePolicyViolationException.class)
    public ResponseEntity<ErrorResponse> handling(final NicknamePolicyViolationException e) {
        return ResponseEntity.badRequest().body(new ErrorResponse(ErrorCode.NICKNAME_POLICY_VIOLATION, e.getMessage()));
    }

    @ExceptionHandler(EmailPolicyViolationException.class)
    public ResponseEntity<ErrorResponse> handling(final EmailPolicyViolationException e) {
        return ResponseEntity.badRequest().body(new ErrorResponse(ErrorCode.EMAIL_POLICY_VIOLATION, e.getMessage()));
    }

    @ExceptionHandler(PasswordPolicyViolationException.class)
    public ResponseEntity<ErrorResponse> handling(final PasswordPolicyViolationException e) {
        return ResponseEntity.badRequest().body(new ErrorResponse(ErrorCode.PASSWORD_POLICY_VIOLATION, e.getMessage()));
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ErrorResponse> handling(JwtException e) {
        return ResponseEntity.badRequest().body(new ErrorResponse(ErrorCode.JWT_ERROR, "사용불가능한 JWT 토큰입니다."));
    }
}
