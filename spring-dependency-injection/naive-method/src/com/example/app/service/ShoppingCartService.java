package com.example.app.service;

import com.example.app.model.Product;
import com.example.app.model.User;

public class ShoppingCartService {

    private final EmailService emailService;

    public ShoppingCartService() {
        emailService = new EmailService();
    }

    public void checkout(Product product, User user) {
        //Logic to check out the product

        //Payment verification

        emailService.emailUser("Order placed successfully for " + product.getName(),
                user.getEmail(),
                "test-email-template.html");
    }
}
