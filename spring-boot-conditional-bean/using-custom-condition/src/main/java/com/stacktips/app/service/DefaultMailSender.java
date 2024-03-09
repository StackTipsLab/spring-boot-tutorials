package com.stacktips.app.service;

import com.stacktips.app.conditions.DefaultMailSenderCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Primary
@Component
@Conditional(DefaultMailSenderCondition.class)
public class DefaultMailSender implements MailSender {

    private final Logger logger = LoggerFactory.getLogger(DefaultMailSender.class);

    @Override
    public void sendMail(String from, String to, String subject) {
        logger.info("Sending email using DefaultMailSender");
    }
}
