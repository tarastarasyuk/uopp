package com.education.uopp.service.impl;

import com.education.uopp.entity.Student;
import com.education.uopp.entity.VerificationToken;
import com.education.uopp.mapper.StudentMapper;
import com.education.uopp.model.StudentDTO;
import com.education.uopp.repository.StudentRepository;
import com.education.uopp.repository.VerificationTokenRepository;
import com.education.uopp.service.StudentService;
import com.education.uopp.validator.EmailValidator;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public Optional<Student> findStudentByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    @Override
    public Integer enableStudent(String email) {
        return studentRepository.enableStudent(email);
    }

}
