package com.stacktips.app.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {
    ADMIN("admin"),
    MENTOR("mentor"),
    STUDENT("student");

    private final String roleDisplayName;

}