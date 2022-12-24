package com.education.uopp.springcourse.repository;

import com.education.uopp.springcourse.model.SCStudent;
import org.springframework.stereotype.Repository;

@Repository
public interface SCStudentRepository extends SCUserRepository<SCStudent> {
}
