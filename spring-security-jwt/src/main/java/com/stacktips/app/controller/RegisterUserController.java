package com.stacktips.app.controller;

import com.stacktips.app.dto.RegisterRequest;
import com.stacktips.app.dto.RegisterResponse;
import com.stacktips.app.services.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/1.0/auth")
@CrossOrigin("*")
@RequiredArgsConstructor
public class RegisterUserController {

    private final RegistrationService registrationService;

    @PostMapping("/register")
    public RegisterResponse registerUser(@RequestBody RegisterRequest request) {
        registrationService.registerUser(request);
        return RegisterResponse.builder().success(true)
                .message("User has been created")
                .build();
    }
}
