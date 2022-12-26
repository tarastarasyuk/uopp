package com.education.uopp.springcourse.repository;

import com.education.uopp.springcourse.model.SCUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SCUserRepository<T extends SCUser> extends JpaRepository<T, Long> {
    Optional<T> findByEmail(String email);

    boolean existsByEmail(String email);
}
