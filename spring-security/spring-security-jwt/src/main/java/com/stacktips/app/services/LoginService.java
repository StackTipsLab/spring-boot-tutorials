package com.stacktips.app.services;

import com.stacktips.app.dto.LoginRequest;
import com.stacktips.app.dto.TokenResponse;
import com.stacktips.app.exception.AuthException;
import com.stacktips.app.exception.UserNotFoundException;
import com.stacktips.app.security.UserDetails;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private static final Logger log = LoggerFactory.getLogger(LoginService.class);
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtTokenProvider jwtTokenProvider;

    public TokenResponse loginUser(LoginRequest loginRequest) throws UserNotFoundException {
        UserDetails userDetails = userDetailsService.loadUserByUsername(
                loginRequest.getEmail());
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(), loginRequest.getPassword());

        try {
            Authentication authenticate = authenticationManager.authenticate(token);
            if (authenticate.isAuthenticated()) {
                return TokenResponse.builder()
                        .success(true)
                        .token(jwtTokenProvider.generateToken(userDetails))
                        .build();
            }
        } catch (BadCredentialsException e) {
            log.error(e.getMessage(), e);
            throw new AuthException("Invalid credentials");
        } catch (DisabledException e) {
            log.error(e.getMessage(), e);
            throw new AuthException("Account not activated");
        } catch (AuthenticationException e) {
            log.error(e.getMessage(), e);
            throw new AuthException(e.getMessage());
        }
        throw new IllegalStateException("Unexpected error during authentication");
    }

}