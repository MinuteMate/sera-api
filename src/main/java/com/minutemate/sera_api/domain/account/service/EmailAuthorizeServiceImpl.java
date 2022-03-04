package com.minutemate.sera_api.domain.account.service;

import com.minutemate.sera_api.domain.account.tokenizer.AuthorizeJwtTokenizer;
import com.minutemate.sera_api.domain.account.config.EmailAuthorizeProperty;
import com.minutemate.sera_api.domain.account.data.entity.AuthorizeNumberEntity;
import com.minutemate.sera_api.domain.account.exception.UnknownAuthorizeNumberException;
import com.minutemate.sera_api.domain.account.repository.AuthorizeNumberRepository;
import com.minutemate.sera_api.infra.service.GoogleSmtpMailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailAuthorizeServiceImpl implements EmailAuthorizeService{
    private final AuthorizeNumberRepository authorizeNumberRepository;
    private final GoogleSmtpMailSenderService googleSmtpMailSenderService;
    private final EmailAuthorizeProperty emailAuthorizeProperty;
    private final AuthorizeJwtTokenizer authorizeJwtTokenizer;

    @Override
    public String generateAuthorizeNumber() {
        while (true) {
            String authorizeNumber = String.format("%06d", generateRandomNumber());
            if(!authorizeNumberRepository.existsById(authorizeNumber)) return authorizeNumber;
        }
    }

    private Integer generateRandomNumber() {
        return new Random().nextInt(1000000);
    }

    @Override
    public void addAuthorizeNumber(String email, String authorizeNumber) {
        authorizeNumberRepository.save(new AuthorizeNumberEntity(authorizeNumber, email));
    }

    @Override
    public void sendAuthorizeNumber(String email, String authorizeNumber) {
        Map<String, Object> models = new HashMap<>();
        models.put("authorize_identifier", authorizeNumber);
        googleSmtpMailSenderService.sendHtmlEmail(email, emailAuthorizeProperty.getTitle(), emailAuthorizeProperty.getTemplatePath(), models);
    }

    @Override
    public String getEmailByNumber(String authorizeNumber) {
        final Optional<AuthorizeNumberEntity> entity = authorizeNumberRepository.findById(authorizeNumber);
        if(entity.isEmpty()) throw new UnknownAuthorizeNumberException(authorizeNumber);
        authorizeNumberRepository.deleteById(authorizeNumber);
        return entity.get().getEmail();
    }

    @Override
    public String getTokenByEmail(String email) {
        return authorizeJwtTokenizer.getTokenByEmail(email);
    }
}
