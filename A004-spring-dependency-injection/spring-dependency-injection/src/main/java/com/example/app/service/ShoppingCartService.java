package com.example.app.service;

import com.example.app.model.Product;
import com.example.app.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShoppingCartService {

    final Logger logger = LoggerFactory.getLogger(ShoppingCartService.class);

    private final EmailService emailService;

    public ShoppingCartService() {
        emailService = new EmailService();
    }

    public void checkout(Product product, User user) {
        logger.info("User {} checked out product:{}", user.getName(),
                product.getName());

        emailService.emailUser("Order placed successfully for " + product.getName(),
                user.getEmail(),
                "test-email-template.html");
    }
}
