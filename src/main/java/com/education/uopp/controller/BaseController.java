package com.education.uopp.controller;

import com.education.uopp.domain.entity.User;
import com.education.uopp.exception.handler.ExceptionHandling;
import com.education.uopp.security.jwt.util.JwtTokenProvider;
import com.education.uopp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.education.uopp.constant.SecurityConstant.JWT_TOKEN_HEADER;

@RestController
@AllArgsConstructor
public class BaseController extends ExceptionHandling {

    private UserService userService;

    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;

    @GetMapping("/hello")
    public String index() {
        return "hello";
    }

    @PostMapping("/sign-in")
    public ResponseEntity<String> login(@RequestBody User user) {
        authenticate(user.getEmail(), user.getPassword());
        User loginUser = (User) userService.loadUserByUsername(user.getEmail());
        return new ResponseEntity<>(jwtTokenProvider.generateJwtToken(loginUser), HttpStatus.OK);
    }

    private HttpHeaders getJwtHeader(User loginUser) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(JWT_TOKEN_HEADER, jwtTokenProvider.generateJwtToken(loginUser));
        return headers;
    }

    private void authenticate(String email, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
    }

    @GetMapping("test")
    public String check() {
        return "test";
    }

    @PreAuthorize("hasRole('ROLE_EDITOR')")
    @GetMapping("teste1")
    public String exc() {
//        throw new BadCredentialsException("f");
        return "editor";
    }

    @PreAuthorize("hasRole('ROLE_STUDENT')")
    @GetMapping("teste2")
    public String exc1() {
//        throw new BadCredentialsException("f");
        return "student";
    }

    @PreAuthorize("hasRole('ROLE_STEFT')")
    @GetMapping("teste3")
    public String exc2() {
//        throw new BadCredentialsException("f");
        return "nothing";
    }
}
