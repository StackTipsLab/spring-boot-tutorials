package com.example.kitchensink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class KitchensinkApplication {

    public static void main(String[] args) {
        SpringApplication.run(KitchensinkApplication.class, args);
    }

}
