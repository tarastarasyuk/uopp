package com.education.uopp.service;

import com.education.uopp.domain.entity.Student;
import com.education.uopp.domain.model.StudentDTO;

import java.util.Optional;

public interface StudentService {
    Student registerStudent(StudentDTO studentDTO);

    Integer enableStudent(String email);

    Optional<Student> findStudentByEmail(String email);
}
