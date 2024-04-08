package com.stacktips.app.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "user")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private boolean active;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<UserPrivilege> privileges;

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MyUser)) {
            return false;
        }

        return ((MyUser) obj).id == this.id;
    }
}
