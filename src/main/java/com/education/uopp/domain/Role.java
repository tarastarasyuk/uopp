package com.education.uopp.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_STUDENT, ROLE_EDITOR;

    @Override
    public String getAuthority() {
        return name();
    }
}
