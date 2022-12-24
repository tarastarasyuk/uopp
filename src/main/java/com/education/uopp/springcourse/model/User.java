package com.education.uopp.springcourse.model;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(of = {"id", "email"})
@NoArgsConstructor
@AllArgsConstructor
public abstract class User {
    private Long id;
    private String email;
    private Role role;

    public User(String email, Role role) {
        this.email = email;
        this.role = role;
    }

    @Override
    public String toString() {
        return "User: {id: %d, email: %s, role: %s}".formatted(id, email, role);
    }
}
