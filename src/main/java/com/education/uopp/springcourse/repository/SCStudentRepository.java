package com.education.uopp.springcourse.repository;

import com.education.uopp.domain.entity.Student;
import org.springframework.stereotype.Repository;

@Repository
public interface SCStudentRepository extends SCUserRepository<Student> {
}
