package com.minutemate.sera_api.domain.account.controller;


import com.minutemate.sera_api.domain.account.data.request.CheckEmailPolicy;
import com.minutemate.sera_api.domain.account.data.request.CheckPasswordPolicy;
import com.minutemate.sera_api.domain.account.exception.email.DuplicateEmailException;
import com.minutemate.sera_api.domain.account.exception.email.UnknownEmailException;
import com.minutemate.sera_api.domain.account.exception.nickname.DuplicateNicknameException;
import com.minutemate.sera_api.domain.account.exception.nickname.OutOfLengthNicknameException;
import com.minutemate.sera_api.domain.account.exception.password.MalformedPasswordException;
import com.minutemate.sera_api.domain.account.exception.password.OutOfLengthPasswordException;
import com.minutemate.sera_api.domain.account.exception.password.WrongPasswordException;
import com.minutemate.sera_api.domain.account.policy.EmailPolicy;
import com.minutemate.sera_api.domain.account.policy.NicknamePolicy;
import com.minutemate.sera_api.domain.account.policy.PasswordPolicy;
import com.minutemate.sera_api.global.data.response.ErrorResponse;
import com.minutemate.sera_api.global.data.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account/policy")
@RequiredArgsConstructor
public class PolicyController {
    private final NicknamePolicy nicknamePolicy;
    private final EmailPolicy emailPolicy;
    private final PasswordPolicy passwordPolicy;

    @GetMapping("/signup/nickname/{nickname}")
    public ResponseEntity<?> checkSignupNicknamePolicy(@PathVariable final String nickname) {
        nicknamePolicy.checkSignupPolicy(nickname);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login/email")
    public ResponseEntity<?> checkLoginEmailPolicy(@RequestBody final CheckEmailPolicy request) {
        emailPolicy.checkSignupPolicy(request.email());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/signup/email")
    public ResponseEntity<?> checkSignupEmailPolicy(@RequestBody final CheckEmailPolicy request) {
        emailPolicy.checkSignupPolicy(request.email());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login/password")
    public ResponseEntity<?> checkLoginPasswordPolicy(@RequestBody final CheckPasswordPolicy request) {
        passwordPolicy.checkSignupPolicy(request.password());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/signup/password")
    public ResponseEntity<?> checkSignupPasswordPolicy(@RequestBody final CheckPasswordPolicy request) {
        passwordPolicy.checkSignupPolicy(request.password());
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(DuplicateNicknameException.class)
    public ResponseEntity<ErrorResponse> handling(final DuplicateNicknameException e) {
        return ResponseEntity.badRequest().body(new ErrorResponse(ErrorCode.DUPLICATE_NICKNAME, e.getMessage()));
    }

    @ExceptionHandler(OutOfLengthNicknameException.class)
    public ResponseEntity<ErrorResponse> handling(final OutOfLengthNicknameException e) {
        return ResponseEntity.badRequest().body(new ErrorResponse(ErrorCode.OUT_OF_LENGTH_NICKNAME, e.getMessage()));
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ErrorResponse> handling(final DuplicateEmailException e) {
        return ResponseEntity.badRequest().body(new ErrorResponse(ErrorCode.DUPLICATE_EMAIL, e.getMessage()));
    }

    @ExceptionHandler(UnknownEmailException.class)
    public ResponseEntity<ErrorResponse> handling(final UnknownEmailException e) {
        return ResponseEntity.badRequest().body(new ErrorResponse(ErrorCode.UNKNOWN_EMAIL, e.getMessage()));
    }

    @ExceptionHandler(MalformedPasswordException.class)
    public ResponseEntity<ErrorResponse> handling(final MalformedPasswordException e) {
        return ResponseEntity.badRequest().body(new ErrorResponse(ErrorCode.MALFORMED_PASSWORD, e.getMessage()));
    }

    @ExceptionHandler(OutOfLengthPasswordException.class)
    public ResponseEntity<ErrorResponse> handling(final OutOfLengthPasswordException e) {
        return ResponseEntity.badRequest().body(new ErrorResponse(ErrorCode.OUT_OF_LENGTH_PASSWORD, e.getMessage()));
    }

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<ErrorResponse> handling(final WrongPasswordException e) {
        return ResponseEntity.badRequest().body(new ErrorResponse(ErrorCode.WRONG_PASSWORD, e.getMessage()));
    }
}
