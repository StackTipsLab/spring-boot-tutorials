package com.stacktips.app.service;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class MailService {

    private final ApplicationContext context;

    public MailService(ApplicationContext context) {
        this.context = context;
    }

    public MailSender getMailSender(String type) {
        if ("ses".equals(type)) {
            return context.getBean("sesMailSender", SesMailSender.class);
        }

        return context.getBean("defaultMailSender", DefaultMailSender.class);
    }

    public void sendMail(String from, String to, String subject) {
        getMailSender("ses").sendMail(from, to, subject);
    }
}