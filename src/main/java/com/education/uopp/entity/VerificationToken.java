package com.education.uopp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class VerificationToken {

    // Expiration time 10 minutes
    private static final int EXPIRATION_TIME = 10;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    private LocalDateTime confirmedAt;

    @OneToOne(optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    public VerificationToken(String token, LocalDateTime createdAt, Student student) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = createdAt.plusMinutes(EXPIRATION_TIME);
        this.student = student;
    }
}
