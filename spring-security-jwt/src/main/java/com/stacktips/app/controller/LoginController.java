package com.stacktips.app.controller;

import com.stacktips.app.dto.LoginRequest;
import com.stacktips.app.dto.TokenResponse;
import com.stacktips.app.exception.UserNotFoundException;
import com.stacktips.app.services.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/1.0/auth/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<TokenResponse> login(@RequestBody @Valid LoginRequest request) throws UserNotFoundException {
        return ResponseEntity.ok(loginService.loginUser(request));
    }
}
