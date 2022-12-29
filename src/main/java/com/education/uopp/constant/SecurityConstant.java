package com.education.uopp.constant;

public final class SecurityConstant {
    public static final long EXPIRATION_TIME = 432_000_000; // 5 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String JWT_TOKEN_HEADER = "Jwt-Token";
    public static final String TOKEN_CANNOT_BE_VERIFIED = "Token cannot be verified";
    public static final String UOPP_LLC = "UOPP, LLC";
    public static final String UOPP_ADMINISTRATION = "User Management portal";
    public static final String AUTHORITIES = "authorities";
    public static final String FORBIDDEN_MESSAGE = "You need to log in to access this page";
    public static final String ACCESS_DENIED_MESSAGE = "You do not have a permission to access this page";
    public static final String OPTIONS_HTTP_METHOD = "OPTIONS";
    public static final String[] PUBLIC_URLS = {
            "/",
            "/register",
            "/verifyRegistration*",
            "/resendVerificationToken*",
            "/login",
            "/get",
            "/sign-up",
            "/sign-in",
            "/opportunities*",
            "/logout"
    };

    public static final String[] EDITOR_URLS = {
            "/editor*",
    };

    public static final String[] STUDENT_URLS = {
            "/profile*"
    };

}
