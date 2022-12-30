package com.education.uopp.service.impl;

import com.education.uopp.domain.Role;
import com.education.uopp.domain.entity.Student;
import com.education.uopp.domain.model.StudentDTO;
import com.education.uopp.exception.EmailTakenException;
import com.education.uopp.exception.InvalidEmailException;
import com.education.uopp.repository.StudentRepository;
import com.education.uopp.repository.VerificationTokenRepository;
import com.education.uopp.service.StudentService;
import com.education.uopp.util.mapper.StudentMapper;
import com.education.uopp.util.validator.EmailValidator;
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
        validateNewUser(studentDTO);

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

    private void validateNewUser(StudentDTO studentDTO) {
        boolean isValidEmail = emailValidator.test(studentDTO.getEmail());

        if (!isValidEmail) {
            throw new InvalidEmailException("Email is not valid");
        }

        findStudentByEmail(studentDTO.getEmail()).flatMap(student -> verificationTokenRepository.findVerificationTokenByStudent(student))
                .ifPresent(verificationToken -> {
                    throw new EmailTakenException("Email already taken");
                });
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
