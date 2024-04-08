package com.stacktips.app.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.stacktips.app.entities.JwtUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.stream.Collectors;


@Slf4j
@Service
public class JwtTokenProvider {
    private final Algorithm algorithm;
    private final JWTVerifier verifier;
    private final String issuer;
    private final String audience;

    public JwtTokenProvider(
            @Value("${jwt.secret}") final String secret,
            @Value("${jwt.issuer}") final String issuer,
            @Value("${jwt.audience}") final String audience) {
        this.issuer = issuer;
        this.audience = audience;
        this.algorithm = Algorithm.HMAC256(secret);
        this.verifier = JWT.require(this.algorithm).build();
    }

    public String generateToken(final JwtUserDetails userDetails) {
        return JWT.create()
                .withClaim("username", userDetails.getUsername())
                .withClaim("id", userDetails.id)
                .withClaim("role", getUserRoles(userDetails))
                .withExpiresAt(Instant.now().plusMillis(Duration.ofMinutes(60).toMillis()))
                .withIssuer(this.issuer)
                .withAudience(this.audience)
                .sign(this.algorithm);
    }

    public long getExpirationInSeconds(String token) {
        Date expiration = verify(token).getExpiresAt();
        Instant now = Instant.now();
        return ChronoUnit.SECONDS.between(now, expiration.toInstant());
    }

    public DecodedJWT verify(final String token) {
        try {
            return verifier.verify(token);
        } catch (final Exception ex) {
            log.info("token invalid", ex);
            return null;
        }
    }

    private String getUserRoles(final UserDetails userDetails) {
        return userDetails.getAuthorities().stream().
                map(Object::toString).
                collect(Collectors.joining(","));
    }
}