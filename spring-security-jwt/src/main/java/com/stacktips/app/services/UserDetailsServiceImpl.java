package com.stacktips.app.services;

import com.stacktips.app.entities.User;
import com.stacktips.app.repositories.UserRepository;
import com.stacktips.app.security.UserDetails;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public UserDetails loadUserByUsername(final String username) {
        Optional<User> userOptional = userRepository.findByEmail(username);
        if (userOptional.isEmpty()) {
            return null;
        }

        User user = userOptional.get();
        List<SimpleGrantedAuthority> authorities = user.getPrivileges()
                .stream()
                .map(p -> new SimpleGrantedAuthority(p.getName()))
                .toList();

        return new UserDetails(user.getEmail(), user.getPassword(), user.isActive(),
                true, true, true, authorities, user.getId());
    }
}