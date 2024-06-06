package com.stacktips.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.withUsername("user")
                        .password("{noop}password")
                        .roles("USER")
                        .build()
        );
    }

//
//    public ClientDetailsService clientDetailsService() {
//        return new ClientDetailsService() {
//            @Override
//            public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
//                BaseClientDetails details = new BaseClientDetails();
//                details.setClientId(clientId);
//                details.setAuthorizedGrantTypes(Arrays.asList("authorization_code") );
//                details.setScope(Arrays.asList("read, trust"));
//                details.setRegisteredRedirectUri(Collections.singleton("http://anywhere.com"));
//                details.setResourceIds(Arrays.asList("oauth2-resource"));
//                Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
//                authorities.add(new SimpleGrantedAuthority("ROLE_CLIENT"));
//                details.setAuthorities(authorities);
//                return details;
//            }
//        };
//    }  //*/


    @Bean
    public AuthenticationManager authenticationManager(
            final AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain configure(final HttpSecurity http) throws Exception {
        return http.cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/hello").permitAll()
                        .requestMatchers("/welcome").authenticated()
                        .anyRequest().permitAll())
                .formLogin(Customizer.withDefaults())
                .csrf(csrf -> csrf.ignoringRequestMatchers("/oauth/token"))
                .formLogin(Customizer.withDefaults())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                .build();
    }

}
