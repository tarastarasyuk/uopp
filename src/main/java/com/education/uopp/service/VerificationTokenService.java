package com.education.uopp.service;

import com.education.uopp.entity.Student;
import com.education.uopp.entity.VerificationToken;

import java.util.Optional;

public interface VerificationTokenService {
    Optional<VerificationToken> findByToken(String token);

    VerificationToken generateVerificationTokenForUser(Student student);

    Boolean isConfirmed(String token);

    String getTokenForStudent(Student user);
    String validateVerificationToken(String token);

    VerificationToken regenerateNewVerificationToken(String oldToken);

    void deleteToken(VerificationToken verificationToken);

    void saveToken(VerificationToken verificationToken);
}
