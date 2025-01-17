package com.stacktips.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                // Allow access to login page and static resources
                .requestMatchers("/login", "/signup", "/css/**", "/js/**").permitAll()

                // public endpoints
                .requestMatchers("/").permitAll()

                // Require authentication for all requests
                .anyRequest().authenticated()
        ).formLogin(formLogin ->
                formLogin.defaultSuccessUrl("/dashboard", true)
                        .loginPage("/login")

        ).logout(logout -> logout.logoutSuccessUrl("/")
                .permitAll()
                .addLogoutHandler(new CookieClearingLogoutHandler())
        );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }

    @Bean
    public UserDetailsManager users(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

}