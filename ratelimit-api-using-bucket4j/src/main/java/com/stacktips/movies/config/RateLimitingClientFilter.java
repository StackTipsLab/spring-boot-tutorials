package com.stacktips.movies.config;

import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
public class RateLimitingClientFilter implements Filter {

    private final BucketConfig bucketConfig;

    private final ConcurrentHashMap<String, Bucket> buckets = new ConcurrentHashMap<>();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String apiKey = httpRequest.getHeader("X-Client-ID");
        if (apiKey == null) {

            httpResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            httpResponse.setContentType(MediaType.TEXT_PLAIN_VALUE);
            httpResponse.getWriter().write("Missing X-Client-ID header");
            return;
        }

        Bucket bucket = buckets.computeIfAbsent(apiKey, this::createNewBucket);
        ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1);
        if (probe.isConsumed()) {
            httpResponse.setHeader("X-Rate-Limit-Remaining", String.valueOf(probe.getRemainingTokens()));
            chain.doFilter(request, response);
        } else {
            httpResponse.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            httpResponse.setHeader("X-Rate-Limit-Retry-After-Seconds",
                    String.valueOf(TimeUnit.NANOSECONDS.toSeconds(probe.getNanosToWaitForRefill())));
            httpResponse.setContentType(MediaType.TEXT_PLAIN_VALUE);
            httpResponse.getWriter().write("Too many requests");
        }
    }

    private Bucket createNewBucket(String apiKey) {
        BucketConfig.ClientBucketConfig config = bucketConfig.getClients().get(apiKey);
        if (config == null) {
            throw new IllegalArgumentException("Unknown API key: " + apiKey);
        }

        return Bucket.builder()
                .addLimit(limit ->
                        limit.capacity(config.getCapacity())
                                .refillIntervally(config.getRefillTokens(), config.getRefillDuration())
                ).build();
    }
}