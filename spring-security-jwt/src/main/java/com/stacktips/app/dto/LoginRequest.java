package com.stacktips.app.dto;

import lombok.*;

import javax.validation.constraints.Email;


@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    @Email(message = "invalid email address")
    String email;

    String password;
}
