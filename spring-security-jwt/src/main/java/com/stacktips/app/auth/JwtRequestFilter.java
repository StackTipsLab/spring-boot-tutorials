package com.stacktips.app.auth;

import com.stacktips.app.services.JwtTokenProvider;
import com.stacktips.app.services.JwtUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

@Component
@AllArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private JwtTokenProvider jwtTokenProvider;
    private JwtUserDetailsService jwtUserDetailsService;

    private boolean isBearerAuthScheme(String value) {
        return StringUtils.startsWithIgnoreCase(value, "Bearer");
    }

    @Override
    protected void doFilterInternal(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final FilterChain chain) throws ServletException, IOException {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || header.isEmpty()) {
            chain.doFilter(request, response);
            return;
        }

        var entries = header.split(",");
        var validToken = Arrays.stream(entries)
                .filter(this::isBearerAuthScheme)
                .map(String::trim)
                .map(s -> s.substring("Bearer ".length()))
                .map(s -> jwtTokenProvider.verify(s))
                .filter(Objects::nonNull)
                .findFirst();

        validToken
                .map(t -> jwtUserDetailsService.loadUserByUsername(t.getClaim("username").asString()))
                .map(ud -> new UsernamePasswordAuthenticationToken(ud, null, ud.getAuthorities()))
                .ifPresent(upat -> {
                    upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(upat);
                });

        chain.doFilter(request, response);
    }
}
