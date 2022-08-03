package com.education.uopp.service;

import com.education.uopp.entity.Student;
import com.education.uopp.entity.VerificationToken;
import com.education.uopp.model.StudentDTO;

import java.util.Optional;

public interface StudentService {

    Integer enableStudent(String email);

    Optional<Student> findStudentByEmail(String email);
}
