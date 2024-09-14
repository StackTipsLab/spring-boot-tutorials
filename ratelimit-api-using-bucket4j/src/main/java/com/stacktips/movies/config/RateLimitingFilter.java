package com.stacktips.movies.config;

import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class RateLimitingFilter implements Filter {

    private final Bucket bucket;

    public RateLimitingFilter() {
        bucket = Bucket.builder()
                .addLimit(limit ->
                        limit.capacity(10)
                                .refillIntervally(1, Duration.ofMinutes(1))
                ).build();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1);

        if (probe.isConsumed()) {
            httpResponse.setHeader("X-Rate-Limit-Remaining", String.valueOf(probe.getRemainingTokens()));
            filterChain.doFilter(servletRequest, httpResponse);
        } else {
            httpResponse.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            httpResponse.setHeader("X-Rate-Limit-Retry-After-Seconds",
                    String.valueOf(TimeUnit.NANOSECONDS.toSeconds(probe.getNanosToWaitForRefill())));
            httpResponse.setContentType(MediaType.TEXT_PLAIN_VALUE);
            httpResponse.getWriter().write("Too many requests");
        }
    }
}