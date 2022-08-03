package com.education.uopp.validator;

import com.education.uopp.entity.User;
import com.education.uopp.service.VerificationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;

import static java.util.Objects.nonNull;

@Service
@AllArgsConstructor
public class ConfirmedUserValidator implements Predicate<User> {

    private final VerificationTokenService verificationTokenService;

    @Override
    public boolean test(User user) {
        return nonNull(verificationTokenService.getTokenForUser(user).get().getConfirmedAt());
    }
}
