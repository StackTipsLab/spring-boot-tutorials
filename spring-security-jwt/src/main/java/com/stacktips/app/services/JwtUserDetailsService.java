package com.stacktips.app.services;

import com.stacktips.app.security.JwtUserDetails;
import com.stacktips.app.entities.User;
import com.stacktips.app.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;


    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public JwtUserDetails loadUserByUsername(final String username) {
        Optional<User> myUserOptional = userRepository.findByEmail(username);
        if (myUserOptional.isEmpty()) {
            return null;
        }

        User user = myUserOptional.get();
        List<SimpleGrantedAuthority> authorities = user.getPrivileges()
                .stream()
                .map(p -> new SimpleGrantedAuthority(p.getName()))
                .toList();

        return new JwtUserDetails(
                user.getId(),
                username,
                user.getPassword(),
                authorities);
    }
}