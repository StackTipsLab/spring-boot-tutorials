package com.stacktips.app.dto;

import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponse {
    boolean success;
    String message;
    String token;
    long expiresIn;
}