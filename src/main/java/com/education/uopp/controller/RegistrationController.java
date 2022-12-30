package com.education.uopp.controller;

import com.education.uopp.domain.entity.Student;
import com.education.uopp.domain.entity.VerificationToken;
import com.education.uopp.email.event.RegistrationCompleteEvent;
import com.education.uopp.domain.model.StudentDTO;
import com.education.uopp.service.StudentService;
import com.education.uopp.service.VerificationTokenService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RegistrationController {

    private final StudentService studentService;
    private final VerificationTokenService verificationTokenService;
    private final ApplicationEventPublisher publisher;

    @Value("${registration.editor-mode}")
    private boolean isEditorRegistrationMode;

    @PostMapping("/sign-up")
    public ResponseEntity<Student> registerStudent(@RequestBody StudentDTO studentDTO, final HttpServletRequest request) {
        Student student = studentService.registerStudent(studentDTO);
        VerificationToken verificationToken = verificationTokenService.generateVerificationTokenForUser(student);

        sendEmailRegistrationToken(request, student, verificationToken);

        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping("/regModeToggle")
    public ResponseEntity<Boolean> editorRegistrationModeToggle() {
        isEditorRegistrationMode = !isEditorRegistrationMode;
        return new ResponseEntity<>(isEditorRegistrationMode, HttpStatus.OK);
    }

    @GetMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam("token") String token) {
        String result = verificationTokenService.validateVerificationToken(token);
        if (result.equalsIgnoreCase("confirmed")) {
            return "Student is verified";
        }
        return "Bad User";
    }

    @GetMapping("/resendVerificationToken")
    public String resendVerificationToken(@RequestParam("token") String oldToken,
                                          HttpServletRequest request) {
        VerificationToken verificationToken = verificationTokenService.regenerateNewVerificationToken(oldToken);

        Student student = verificationToken.getStudent();
        sendEmailRegistrationToken(request, student, verificationToken);

        return "Verification link is resend";
    }


    private String applicationUrl(HttpServletRequest request) {
        return "http://" +
                request.getServerName() +
                ":" +
                request.getServerPort() + "/" +
                request.getContextPath();
    }

    private void sendEmailRegistrationToken(HttpServletRequest request, Student student, VerificationToken verificationToken) {
        publisher.publishEvent(new RegistrationCompleteEvent(
                student,
                applicationUrl(request),
                verificationToken
        ));
    }
}
