package com.education.uopp.service;

import com.education.uopp.entity.Student;
import com.education.uopp.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    Optional<User> findUserByEmail(String email);
}
