package com.education.uopp.springcourse.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "sc_editor")
public class SCEditor extends SCUser {
    public SCEditor(Long userId, String email, SCRole role) {
        super(userId, email, role);
    }

    public SCEditor(String email) {
        super(email, SCRole.ROLE_EDITOR);
    }

    @Override
    public String toString() {
        return "Editor: { %s }".formatted(super.toString());
    }
}
