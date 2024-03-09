package com.stacktips.app.service;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
public class MailService {

    private final ApplicationContext context;

    public MailService(ApplicationContext context) {
        this.context = context;
    }

    public MailSender getMailSender(String type) {
        Map<String, Object> beansWithAnnotation = context.getBeansWithAnnotation(MailSenderSelector.class);
        Optional<Object> matchingBean = beansWithAnnotation.values().stream()
                .filter(bean -> bean.getClass().getAnnotation(MailSenderSelector.class).value().equals(type))
                .findFirst();

        if (matchingBean.isEmpty()) {
            throw new IllegalArgumentException("No bean found for type: " + type);
        }

        return (MailSender) matchingBean.get();
    }

    public void sendMail(String from, String to, String subject) {
        getMailSender("ses").sendMail(from, to, subject);
    }
}