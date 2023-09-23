package com.example.app.service;

import com.example.app.model.User;

public class UserService {

    private final UserDao userDao;
    private final EmailService emailService;

    public UserService() {
        userDao = new UserDao();
        emailService = new EmailService();
    }

    public void registerUser(User user) {
        userDao.saveUser(user);

        emailService.emailUser("User registered successfully!",
                user.getEmail(),
                "new-user-email.html");
    }
}
