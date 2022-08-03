package com.education.uopp.registration;

import com.education.uopp.model.Role;
import lombok.*;

@Data
public class UserRegistrationRequest {
    private final String email;
    private final String password;
}
