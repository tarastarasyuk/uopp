package com.education.uopp.repository;

import com.education.uopp.entity.Student;
import com.education.uopp.entity.User;
import com.education.uopp.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    Optional<VerificationToken> findByToken(String token);

    @Transactional
    @Modifying
    @Query("UPDATE VerificationToken c " +
            "SET c.confirmedAt = ?2 " +
            "WHERE c.token = ?1")
    Integer updateConfirmedAt(String token,
                              LocalDateTime confirmedAt);

    boolean existsByTokenAndConfirmedAtIsNotNull(String token);

    Optional<VerificationToken> findVerificationTokenByUser(User user);
}
