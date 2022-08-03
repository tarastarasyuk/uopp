package com.education.uopp.registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private final UserRegistrationService userRegistrationService;

    @PostMapping
    private String register(@RequestBody UserRegistrationRequest request) {
        return userRegistrationService.register(request);
    }

    @GetMapping(path = "confirm")
    private String confirm(@RequestParam("token") String token) {
        return userRegistrationService.confirmToken(token);
    }

}
