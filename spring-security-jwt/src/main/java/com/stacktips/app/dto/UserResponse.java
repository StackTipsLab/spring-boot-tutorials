package com.stacktips.app.dto;

import com.stacktips.app.entities.UserPrivilege;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    Long id;
    String email;
    String firstName;
    String lastName;
    Set<UserPrivilege> privileges;
}
