package com.education.uopp.service.impl;

import com.education.uopp.entity.Student;
import com.education.uopp.entity.User;
import com.education.uopp.entity.VerificationToken;
import com.education.uopp.repository.StudentRepository;
import com.education.uopp.repository.UserRepository;
import com.education.uopp.repository.VerificationTokenRepository;
import com.education.uopp.service.VerificationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static java.util.Objects.nonNull;

@AllArgsConstructor
@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {

    private VerificationTokenRepository verificationTokenRepository;

    private UserRepository userRepository;

    @Override
    public Optional<VerificationToken> findByToken(String token) {
        return verificationTokenRepository.findByToken(token);
    }

    @Override
    public VerificationToken generateVerificationTokenForUser(Student student) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken(token, LocalDateTime.now(), student);
        return verificationTokenRepository.save(verificationToken);
    }

    @Override
    public String validateVerificationToken(String token) {
        VerificationToken verificationToken = findByToken(token)
                .orElseThrow(() -> new IllegalStateException("Token is not found"));

        if (verificationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("Email is already confirmed");
        }

        LocalDateTime expiredAt = verificationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Token is expired");
        }

        setConfirmedAt(token);

        User user = verificationToken.getUser();
        userRepository.enableUser(user.getEmail());
        return "confirmed";
    }

    public Integer setConfirmedAt(String token) {
        return verificationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
    }

    @Override
    public VerificationToken regenerateNewVerificationToken(String oldToken) {
        VerificationToken verificationToken = findByToken(oldToken)
                .orElseThrow(() -> new IllegalStateException("Token for resend is not valid! Try to contact support"));

        if (nonNull(verificationToken.getConfirmedAt())) {
            throw new IllegalStateException("Token was confirmed. Connect our support if there is a mistake");
        }

        verificationToken.setToken(UUID.randomUUID().toString());
        saveToken(verificationToken);
        return verificationToken;
    }

    @Override
    public Optional<VerificationToken> getTokenForUser(User user) {
        return verificationTokenRepository.findVerificationTokenByUser(user);
    }

    @Override
    public Boolean isConfirmed(String token) {
        return verificationTokenRepository.existsByTokenAndConfirmedAtIsNotNull(token);
    }

    @Override
    public void deleteToken(VerificationToken verificationToken) {
        verificationTokenRepository.delete(verificationToken);
    }

    @Override
    public void saveToken(VerificationToken verificationToken) {
        verificationTokenRepository.save(verificationToken);
    }
}
