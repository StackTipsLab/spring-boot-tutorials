package com.example.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailService {

    final Logger logger = LoggerFactory.getLogger(EmailService.class);

    public void emailUser(String subject, String toEmail, String template) {
        logger.info("Sending mail to email:{}, subject:{}, template:{}",
                toEmail, subject, template);
    }
}
