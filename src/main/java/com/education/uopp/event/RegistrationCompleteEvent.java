package com.education.uopp.event;

import com.education.uopp.entity.Student;
import com.education.uopp.entity.User;
import com.education.uopp.entity.VerificationToken;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent {

    private User user;
    private String applicationUrl;

    private VerificationToken verificationToken;

    public RegistrationCompleteEvent(User user, String applicationUrl, VerificationToken verificationToken) {
        super(user);
        this.user = user;
        this.applicationUrl = applicationUrl;
        this.verificationToken = verificationToken;
    }

}
