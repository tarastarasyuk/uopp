package com.education.uopp.validator;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
@AllArgsConstructor
public class EmailValidator implements Predicate<String> {

    @Override
    public boolean test(String s) {
        // TODO: Regex to validate an email with configuration - validation rule
        return true;
    }

}
