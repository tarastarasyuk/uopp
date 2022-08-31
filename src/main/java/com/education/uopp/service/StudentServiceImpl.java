package com.education.uopp.service;

import com.education.uopp.entity.Student;
import com.education.uopp.entity.VerificationToken;
import com.education.uopp.mapper.StudentMapper;
import com.education.uopp.model.StudentDTO;
import com.education.uopp.repository.StudentRepository;
import com.education.uopp.repository.VerificationTokenRepository;
import com.education.uopp.validator.EmailValidator;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private VerificationTokenRepository verificationTokenRepository;

    private final EmailValidator emailValidator;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Student registerStudent(StudentDTO studentDTO) {
        boolean isValidEmail = emailValidator.test(studentDTO.getEmail());
        if (!isValidEmail) {
            // TODO: create an exception for validation
            throw new IllegalStateException("Email is not valid");
        }

        findStudentByEmail(studentDTO.getEmail()).flatMap(student -> verificationTokenRepository.findVerificationTokenByStudent(student))
                .ifPresent(verificationToken -> {
                    if (verificationTokenRepository.existsByTokenAndConfirmedAtIsNotNull(verificationToken.getToken())) {
                        // TODO: create an exception already taken
                        throw new IllegalStateException("Email already taken");
                    } else {
                        // TODO: create an exception for that issue | maybe remove this check
                        throw new IllegalStateException("Email was already taken, but not confirmed.");
                    }
                });

        String encodedPassword = passwordEncoder.encode(studentDTO.getPassword());
        studentDTO.setPassword(encodedPassword);


        Student student = StudentMapper.INSTANCE.studentDTOtoStudent(studentDTO);
        return studentRepository.save(student);
    }


    @Override
    public Optional<Student> findStudentByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    @Override
    public Integer enableStudent(String email) {
        return studentRepository.enableStudent(email);
    }

}
