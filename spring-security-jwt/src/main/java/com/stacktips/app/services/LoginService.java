package com.stacktips.app.services;

import com.stacktips.app.dto.LoginRequest;
import com.stacktips.app.dto.TokenResponse;
import com.stacktips.app.security.JwtUserDetails;
import com.stacktips.app.exception.AccountNotActivatedException;
import com.stacktips.app.exception.InvalidPasswordException;
import com.stacktips.app.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final JwtUserDetailsService userDetailsService;
    private final JwtTokenProvider jwtTokenProvider;

    public TokenResponse loginUser(LoginRequest loginRequest) throws UserNotFoundException {
        JwtUserDetails userDetails = userDetailsService.loadUserByUsername(
                loginRequest.getEmail());
        isAccountActive(userDetails);

        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    userDetails.getUsername(), loginRequest.getPassword());
            Authentication authenticate = authenticationManager.authenticate(token);
            if (authenticate.isAuthenticated()) {
                String jwtToken = jwtTokenProvider.generateToken(userDetails);
                return TokenResponse.builder()
                        .success(true)
                        .message("Token generated successfully")
                        .expiresIn(jwtTokenProvider.getExpirationInSeconds(jwtToken))
                        .token(jwtToken).build();
            }
        } catch (BadCredentialsException e) {
            throw new InvalidPasswordException("Invalid password for username: " + userDetails.getUsername());
        }
        throw new IllegalStateException("Unexpected error during authentication");
    }

    private static void isAccountActive(JwtUserDetails userDetails) {
        if (!userDetails.isEnabled()) {
            throw new AccountNotActivatedException("Your account is not active.");
        }
    }
}