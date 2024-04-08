package com.stacktips.app.controller;

import com.stacktips.app.dto.RegisterRequest;
import com.stacktips.app.services.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/1.0/auth")
@RequiredArgsConstructor
public class RegisterController {

    private final RegistrationService registrationService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request) {
        registrationService.registerUser(request);
        return ResponseEntity.ok("User has been created");
    }

    @GetMapping("/token/activation")
    public ResponseEntity<?> activateAccount(@RequestParam String token) {
        boolean isActivated = registrationService.activateAccount(token);
        if (isActivated) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Account successfully activated.");
        } else {
            return ResponseEntity.badRequest().body("Invalid or expired activation token.");
        }
    }
}
