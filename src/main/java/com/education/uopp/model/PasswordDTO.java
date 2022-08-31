package com.education.uopp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordDTO {
    private String email;
    private String oldPassword;
    private String newPassword;
}
