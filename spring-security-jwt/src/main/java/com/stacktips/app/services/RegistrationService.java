package com.stacktips.app.services;

import com.stacktips.app.dto.RegisterRequest;
import com.stacktips.app.entities.User;
import com.stacktips.app.exception.UserAlreadyExistsException;
import com.stacktips.app.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public void registerUser(RegisterRequest request) {
        Optional<User> hasUser = userRepository.findByEmail(request.getEmail());
        if (hasUser.isPresent()) {
            throw new UserAlreadyExistsException("User already registered!");
        }

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .active(true) //TODO Send activation email
                .id(null).build();

        userRepository.save(user);
    }
}