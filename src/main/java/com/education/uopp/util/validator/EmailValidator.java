package com.education.uopp.util.validator;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class EmailValidator implements Predicate<String> {
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.\\-]+@[a-zA-Z0-9.\\-]+$";

    @Override
    public boolean test(String s) {
        return Pattern.compile(EMAIL_REGEX)
                .matcher(s)
                .matches();
    }
}
