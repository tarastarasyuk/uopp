package com.education.uopp.controller;

import com.education.uopp.domain.SignInResponse;
import com.education.uopp.domain.entity.User;
import com.education.uopp.exception.handler.ExceptionHandling;
import com.education.uopp.security.jwt.util.JwtTokenProvider;
import com.education.uopp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class BaseController extends ExceptionHandling {

    private UserService userService;

    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/sign-in")
    public ResponseEntity<String> login(@RequestBody User user) {
        authenticate(user.getEmail(), user.getPassword());
        User loginUser = (User) userService.loadUserByUsername(user.getEmail());
        final String token = jwtTokenProvider.generateJwtToken(loginUser);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    private void authenticate(String email, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
    }
}
