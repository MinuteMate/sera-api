package com.minutemate.sera_api.domain.account.controller;

import com.minutemate.sera_api.domain.account.data.request.SendAuthorizeNumberToEmailRequest;
import com.minutemate.sera_api.domain.account.data.response.GetAuthorizeTokenByNumberResponse;
import com.minutemate.sera_api.domain.account.exception.UnknownAuthorizeNumberException;
import com.minutemate.sera_api.domain.account.service.EmailAuthorizeService;
import com.minutemate.sera_api.infra.exception.MailSendingException;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/authorize/email")
@RequiredArgsConstructor
public class EmailAuthorizeController {
    private final EmailAuthorizeService emailAuthorizeService;

    @PostMapping
    public ResponseEntity<?> sendAuthorizeNumberToEmail(@RequestBody final SendAuthorizeNumberToEmailRequest request) {
        final String authorizeNumber = emailAuthorizeService.generateAuthorizeNumber();
        emailAuthorizeService.addAuthorizeNumber(request.email(), authorizeNumber);
        emailAuthorizeService.sendAuthorizeNumber(request.email(), authorizeNumber);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{number}")
    public ResponseEntity<GetAuthorizeTokenByNumberResponse> getAuthorizeTokenByNumber(@PathVariable final String number) {
        final String email = emailAuthorizeService.getEmailByNumber(number);
        final String token = emailAuthorizeService.getTokenByEmail(email);
        final GetAuthorizeTokenByNumberResponse response = new GetAuthorizeTokenByNumberResponse(token);
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(UnknownAuthorizeNumberException.class)
    public ResponseEntity<?> handling(UnknownAuthorizeNumberException e) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MailSendingException.class)
    public ResponseEntity<?> handling(MailSendingException e) {
        return ResponseEntity.internalServerError().build();
    }
}
