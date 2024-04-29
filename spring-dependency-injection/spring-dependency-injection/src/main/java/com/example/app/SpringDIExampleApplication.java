package com.example.app;

import com.example.app.model.Product;
import com.example.app.model.User;
import com.example.app.service.EmailService;
import com.example.app.service.ShoppingCartService;
import com.example.app.service.UserRepository;
import com.example.app.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringDIExampleApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringDIExampleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User("john@example.com", "John Doe");

        UserService userService = new UserService();
        userService.registerUser(user);

        Product product = new Product(1, "iPhone 15", 1000.00);
        ShoppingCartService shoppingCartService = new ShoppingCartService();
        shoppingCartService.checkout(product, user);
    }
}
