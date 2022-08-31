package com.education.uopp.event;

import com.education.uopp.entity.Student;
import com.education.uopp.entity.VerificationToken;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent {

    private Student student;
    private String applicationUrl;


    private VerificationToken verificationToken;

    public RegistrationCompleteEvent(Student student, String applicationUrl, VerificationToken verificationToken) {
        super(student);
        this.student = student;
        this.applicationUrl = applicationUrl;
        this.verificationToken = verificationToken;
    }

}
