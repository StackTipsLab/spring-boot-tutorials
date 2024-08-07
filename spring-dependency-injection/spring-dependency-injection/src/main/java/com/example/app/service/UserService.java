package com.example.app.service;

import com.example.app.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserService {

    final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final EmailService emailService;
    private final UserRepository userRepository;

    public UserService() {
        emailService = new EmailService();
        userRepository = new UserRepository();
    }


    public void registerUser(User user) {
        userRepository.saveUser(user);
        logger.info("User registered successfully!");

        emailService.emailUser("User registered successfully!",
                user.getEmail(),
                "new-user-email.html");
    }
}
