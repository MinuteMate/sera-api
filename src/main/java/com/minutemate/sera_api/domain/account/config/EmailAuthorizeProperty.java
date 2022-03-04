package com.minutemate.sera_api.domain.account.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties("authorize.email")
@Configuration
@Getter @Setter
public class EmailAuthorizeProperty {
    private String title;
    private String templatePath;
}
