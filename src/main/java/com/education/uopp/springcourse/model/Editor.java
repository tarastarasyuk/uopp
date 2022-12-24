package com.education.uopp.springcourse.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = {"id"}, callSuper = true)
@NoArgsConstructor
public class Editor extends User {
    private Long id;

    public Editor(Long userId, String email, Long editorId) {
        super(userId, email, Role.ROLE_EDITOR);
        this.id = editorId;
    }

    public Editor(String email) {
        super(email, Role.ROLE_EDITOR);
    }

    @Override
    public String toString() {
        return "Editor: {%s, id: %d}".formatted(super.toString(), id);
    }
}
