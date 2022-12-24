package com.education.uopp.springcourse.repository;

import com.education.uopp.springcourse.model.Opportunity;
import com.education.uopp.springcourse.model.Student;

public interface StudentRepository extends UserRepository<Student, Long> {
    void likeUnlikeOpportunity(Student student, Opportunity opportunity);
}
