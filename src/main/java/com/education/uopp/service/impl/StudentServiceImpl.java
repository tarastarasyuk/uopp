package com.education.uopp.service.impl;

import com.education.uopp.domain.Role;
import com.education.uopp.domain.entity.Student;
import com.education.uopp.service.StudentService;
import com.education.uopp.util.mapper.StudentMapper;
import com.education.uopp.domain.model.StudentDTO;
import com.education.uopp.repository.StudentRepository;
import com.education.uopp.repository.VerificationTokenRepository;
import com.education.uopp.util.validator.EmailValidator;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final VerificationTokenRepository verificationTokenRepository;

    private final EmailValidator emailValidator;

    private final PasswordEncoder passwordEncoder;

    private final StudentMapper studentMapper;

    @Value("${registration.editor-mode}")
    private boolean isEditorRegistrationMode;

    @Override
    public Student registerStudent(StudentDTO studentDTO) {
        boolean isValidEmail = emailValidator.test(studentDTO.getEmail());

        // TODO: create validateNewUser method to avoid current method overloading
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


        Student student = studentMapper.studentDTOtoStudent(studentDTO);
        // TODO: remove, only for testing purpose
        editorRegistrationHack(student);
        return studentRepository.save(student);
    }

    private void editorRegistrationHack(Student student) {
        if (isEditorRegistrationMode) {
            student.setRole(Role.ROLE_EDITOR);
        }
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
