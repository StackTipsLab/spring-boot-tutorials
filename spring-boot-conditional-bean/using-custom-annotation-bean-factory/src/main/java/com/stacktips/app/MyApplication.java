package com.stacktips.app;

import com.stacktips.app.service.MailService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyApplication implements CommandLineRunner {

    private final MailService mailService;

    public MyApplication(MailService mailService) {
        this.mailService = mailService;
    }

    @Override
    public void run(String... args) throws Exception {
        mailService.sendMail("hello@test.com", "john.doe@gmail.com", "Test mail");
    }

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}
