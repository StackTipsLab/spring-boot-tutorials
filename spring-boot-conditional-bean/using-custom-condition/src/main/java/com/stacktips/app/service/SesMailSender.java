package com.stacktips.app.service;

import com.stacktips.app.conditions.SesMailSenderCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;

@Service
@Conditional(SesMailSenderCondition.class)
public class SesMailSender implements MailSender {

    private final Logger logger = LoggerFactory.getLogger(SesMailSender.class);

    @Override
    public void sendMail(String from, String to, String subject) {
        logger.info("Sending email using SesMailSender");
    }
}
