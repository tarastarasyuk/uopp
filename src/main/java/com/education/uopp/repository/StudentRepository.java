package com.education.uopp.repository;

import com.education.uopp.domain.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE Student a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    Integer enableStudent(String email);

    Optional<Student> findByEmail(String email);
}
