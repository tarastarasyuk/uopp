package com.education.uopp.controller;

import com.education.uopp.entity.Student;
import com.education.uopp.entity.User;
import com.education.uopp.entity.VerificationToken;
import com.education.uopp.event.RegistrationCompleteEvent;
import com.education.uopp.model.StudentDTO;
import com.education.uopp.service.StudentRegistrationService;
import com.education.uopp.service.StudentService;
import com.education.uopp.service.VerificationTokenService;
import com.education.uopp.service.impl.StudentRegistrationServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@AllArgsConstructor
@Slf4j
public class RegistrationController {

    private final StudentRegistrationService studentRegistrationService;
    private final VerificationTokenService verificationTokenService;
    private ApplicationEventPublisher publisher;


    @PostMapping("/register")
    public ResponseEntity<Student> registerStudent(@RequestBody StudentDTO studentDTO, final HttpServletRequest request) {
        Student student = studentRegistrationService.registerStudent(studentDTO);
        VerificationToken verificationToken = verificationTokenService.generateVerificationTokenForUser(student);

        sendEmailRegistrationToken(request, student, verificationToken);

        return new ResponseEntity<>(student, HttpStatus.OK);
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

        User user = verificationToken.getUser();
        sendEmailRegistrationToken(request, user, verificationToken);

        return "Verification link is resend";
    }


    private String applicationUrl(HttpServletRequest request) {
        return "http://" +
                request.getServerName() +
                ":" +
                request.getServerPort() + "/" +
                request.getContextPath();
    }

    private void sendEmailRegistrationToken(HttpServletRequest request, User user, VerificationToken verificationToken) {
        publisher.publishEvent(new RegistrationCompleteEvent(
                user,
                applicationUrl(request),
                verificationToken
        ));
    }
}
