package com.education.uopp.exception.handler;

import com.education.uopp.domain.HttpResponse;
import com.education.uopp.exception.EmailTakenException;
import com.education.uopp.exception.InvalidEmailException;
import com.education.uopp.exception.TokenValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionHandling implements ErrorController {
    private static final String ACCOUNT_LOCKED = "ACCOUNT_LOCKED";
    private static final String METHOD_IN_NOT_ALLOWED = "METHOD_IN_NOT_ALLOWED";
    private static final String INTERNAL_SERVER_ERROR_MSG = "INTERNAL_SERVER_ERROR_MSG";
    private static final String INCORRECT_CREDENTIALS = "INCORRECT_CREDENTIALS";
    private static final String ACCOUNT_DISABLED = "ACCOUNT_DISABLED";
    private static final String ERROR_PROCESSING_FILE = "ERROR_PROCESSING_FILE";
    private static final String NOT_ENOUGH_PERMISSION = "NOT_ENOUGH_PERMISSION";
    private static final String ERROR_PATH = "/error";

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<HttpResponse> accountDisabledException() {
        return createHttpResponse(HttpStatus.BAD_REQUEST, ACCOUNT_DISABLED);
    }

    @ExceptionHandler(LockedException.class)
    public ResponseEntity<HttpResponse> accountLockedException() {
        return createHttpResponse(HttpStatus.BAD_REQUEST, ACCOUNT_LOCKED);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<HttpResponse> badCredentialsException() {
        return createHttpResponse(HttpStatus.FORBIDDEN, INCORRECT_CREDENTIALS);
    }


    @ExceptionHandler(TokenValidationException.class)
    public ResponseEntity<HttpResponse> tokenExpiredException(TokenValidationException e) {
        return createHttpResponse(HttpStatus.UNAUTHORIZED, e.getMessage().toUpperCase());
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<HttpResponse> invalidEmailException(InvalidEmailException e) {
        return createHttpResponse(HttpStatus.BAD_REQUEST, e.getMessage().toUpperCase());
    }

    @ExceptionHandler(EmailTakenException.class)
    public ResponseEntity<HttpResponse> invalidEmailException(EmailTakenException e) {
        return createHttpResponse(HttpStatus.BAD_REQUEST, e.getMessage().toUpperCase());
    }

//    not to block other pages - /error is general, not applicable here
//    @RequestMapping(ERROR_PATH)
//    public ResponseEntity<HttpResponse> notFound404() {
//        return createHttpResponse(HttpStatus.NOT_FOUND, "There is no mapping for this URL");
//    }

    private ResponseEntity<HttpResponse> createHttpResponse(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(),
                httpStatus, httpStatus.getReasonPhrase().toUpperCase(), message), httpStatus);
    }
}
