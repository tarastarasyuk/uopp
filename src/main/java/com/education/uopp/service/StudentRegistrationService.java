package com.education.uopp.service;

import com.education.uopp.entity.Student;
import com.education.uopp.model.StudentDTO;
import org.springframework.stereotype.Service;

@Service
public interface StudentRegistrationService {
    Student registerStudent(StudentDTO studentDTO);
}
