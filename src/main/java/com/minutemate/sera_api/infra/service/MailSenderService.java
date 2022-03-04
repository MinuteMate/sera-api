package com.minutemate.sera_api.infra.service;

import java.util.Map;

public interface MailSenderService {
    void sendHtmlEmail(String email, String title, String templatePath, Map<String, Object> models);
}
