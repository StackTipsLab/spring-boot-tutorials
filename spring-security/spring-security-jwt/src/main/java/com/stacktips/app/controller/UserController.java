package com.stacktips.app.controller;

import com.stacktips.app.dto.UserResponse;
import com.stacktips.app.entities.User;
import com.stacktips.app.exception.UserNotFoundException;
import com.stacktips.app.services.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/1.0/users")
@RequiredArgsConstructor
public class UserController {

    private final UserDetailsServiceImpl userDetailsService;

    @GetMapping(path = "/{userId}")
    public UserResponse getUser(@PathVariable Long userId) {
        Optional<User> user = userDetailsService.getUser(userId);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User for id '" + userId + "' not found!");
        }

        return UserResponse.builder()
                .id(user.get().getId())
                .email(user.get().getEmail())
                .firstName(user.get().getFirstName())
                .lastName(user.get().getLastName())
                .privileges(user.get().getPrivileges())
                .build();
    }
}
