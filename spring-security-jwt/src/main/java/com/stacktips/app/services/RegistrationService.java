package com.stacktips.app.services;

import com.stacktips.app.dto.RegisterRequest;
import com.stacktips.app.entities.MyUser;
import com.stacktips.app.exception.UserAlreadyExistsException;
import com.stacktips.app.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public void registerUser(RegisterRequest request) {
        Optional<MyUser> hasUser = userRepository.findByEmail(request.getEmail());
        if (hasUser.isPresent()) {
            throw new UserAlreadyExistsException("User already registered!");
        }

        MyUser user = MyUser.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .lastName(request.getFirstName())
                .lastName(request.getLastName())
                .active(true) //TODO currently hardcoded
                .build();
        userRepository.save(user);

//        String token = accountActivationTokenGenerator(student);
//        registerStudentRequest.setTemporaryToken(token);
//        return registerStudentRequest;

        //TODO Send activation email here.
    }

    public boolean activateAccount(String token) {
        // Search for the activation token in the database
//        Optional<AccountActivation> tokenRecord = accountActivationRepository.findByActivationToken(token);
//
//        // Check if the token exists and the account has not already been activated
//        if (tokenRecord.isPresent() && !tokenRecord.get().getActivated()) {
//            AccountActivation activation = tokenRecord.get();
//
//            // Check if the token has not expired
//            if (activation.getExpirationDate().isAfter(LocalDateTime.now())) {
//
//                // Retrieve the student associated with the token
//                Student student = studentRepository.findById(activation.getStudent_id()).orElse(null);
//                if (student != null) {
//                    // Activate the student account
//                    student.setAccount_activate(true);
//                    studentRepository.save(student);
//
//                    // Mark the token as used
//                    activation.setActivated(true);
//                    accountActivationRepository.save(activation);
//
//                    return true;
//                }
//            }
//        }
        // Token does not exist, has expired, or the account has already been activated
        return false;
    }
}