package com.stacktips.app.entities;

import jakarta.persistence.*;
import lombok.*;


@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPrivilege {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;

    public enum Role {
        CUSTOMER,
        SITE_ADMIN;

        private final String privilege;

        Role() {
            privilege = "ROLE_" + name();
        }

        public String privilege() {
            return privilege;
        }
    }
}
