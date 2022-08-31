package com.education.uopp.model;

import com.education.uopp.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

    private String email;

    private String password;

    private Role role;

    private String firstName;

    private String lastName;

    private String gender;

}
