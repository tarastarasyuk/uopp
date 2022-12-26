package com.education.uopp.springcourse.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(of = {"id", "email"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sc_user")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class SCUser {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SCRole role;

    protected SCUser(String email, SCRole role) {
        this.email = email;
        this.role = role;
    }

    @Override
    public String toString() {
        return "User: { id: %d, email: %s, role: %s }".formatted(id, email, role);
    }
}
