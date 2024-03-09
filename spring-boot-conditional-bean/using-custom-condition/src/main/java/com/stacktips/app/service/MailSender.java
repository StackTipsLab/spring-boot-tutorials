package com.stacktips.app.service;


public interface MailSender {

    void sendMail(String from, String to, String subject);
}
