package com.stacktips.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@MailSenderSelector("default")
public class DefaultMailSender implements MailSender {

    private final Logger logger = LoggerFactory.getLogger(DefaultMailSender.class);

    @Override
    public void sendMail(String from, String to, String subject) {
        logger.info("Sending email using DefaultMailSender");
    }
}
