package com.example.app.service;

import com.example.app.model.User;

public class UserService {

    private final EmailService emailService;
    private final UserDao userDao;

    public UserService() {
        emailService = new EmailService();
        userDao = new UserDao();
    }

    public void registerUser(User user) {
        userDao.saveUser(user);

        emailService.emailUser("User registered successfully!",
                user.getEmail(),
                "new-user-email.html");
    }
}
