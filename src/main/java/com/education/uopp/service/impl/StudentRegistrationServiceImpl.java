package com.education.uopp.service.impl;

import com.education.uopp.entity.Student;
import com.education.uopp.mapper.StudentMapper;
import com.education.uopp.model.StudentDTO;
import com.education.uopp.repository.StudentRepository;
import com.education.uopp.repository.VerificationTokenRepository;
import com.education.uopp.service.StudentRegistrationService;
import com.education.uopp.service.StudentService;
import com.education.uopp.validator.EmailValidator;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StudentRegistrationServiceImpl implements StudentRegistrationService {
    private VerificationTokenRepository verificationTokenRepository;

    private final EmailValidator emailValidator;

    private final PasswordEncoder bCryptPasswordEncoder;

    private final StudentService studentService;

    private final StudentRepository studentRepository;

    @Override
    public Student registerStudent(StudentDTO studentDTO) {
        boolean isValidEmail = emailValidator.test(studentDTO.getEmail());
        if (!isValidEmail) {
            // TODO: create an exception for validation
            throw new IllegalStateException("Email is not valid");
        }

        studentService.findStudentByEmail(studentDTO.getEmail()).flatMap(student -> verificationTokenRepository.findVerificationTokenByUser(student))
                .ifPresent(verificationToken -> {
                    if (verificationTokenRepository.existsByTokenAndConfirmedAtIsNotNull(verificationToken.getToken())) {
                        // TODO: create an exception already taken
                        throw new IllegalStateException("Email already taken");
                    } else {
                        // TODO: create an exception for that issue | maybe remove this check
                        throw new IllegalStateException("Email was already taken, but not confirmed.");
                    }
                });

        String encodedPassword = bCryptPasswordEncoder.encode(studentDTO.getPassword());
        studentDTO.setPassword(encodedPassword);


        Student student = StudentMapper.INSTANCE.studentDTOtoStudent(studentDTO);
        return studentRepository.save(student);
    }

}
