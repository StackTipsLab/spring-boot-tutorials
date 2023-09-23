package com.example.app;

import com.example.app.model.Product;
import com.example.app.model.User;
import com.example.app.service.ShoppingCartService;
import com.example.app.service.UserService;

public class Main {

    public static void main(String[] args) {
        User user = new User("john@example.com", "John Doe");
        UserService userService = new UserService();
        userService.registerUser(user);

        Product product = new Product(1, "iPhone 15", 1000.00);
        ShoppingCartService shoppingCartService = new ShoppingCartService();
        shoppingCartService.checkout(product, user);
    }
}