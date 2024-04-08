package com.stacktips.app.services;

import com.stacktips.app.entities.JwtUserDetails;
import com.stacktips.app.entities.MyUser;
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

    @Override
    public JwtUserDetails loadUserByUsername(final String username) {
        Optional<MyUser> myUserOptional = userRepository.findByEmail(username);
        if (myUserOptional.isEmpty()) {
            return null;
        }

        MyUser myUser = myUserOptional.get();

        List<SimpleGrantedAuthority> authorities = myUser.getPrivileges()
                .stream()
                .map(p -> new SimpleGrantedAuthority(p.getName()))
                .toList();

        return new JwtUserDetails(
                myUser.getId(),
                username,
                myUser.getPassword(),
                authorities);
    }
}