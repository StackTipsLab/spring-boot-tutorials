package com.stacktips.app;

import com.stacktips.app.service.MailSender;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyApplication implements CommandLineRunner {

    private final MailSender mailSender;

    public MyApplication(@Qualifier("defaultMailSender") MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void run(String... args) throws Exception {
        mailSender.sendMail("hello@test.com", "john.doe@gmail.com", "Test mail");
    }

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}
