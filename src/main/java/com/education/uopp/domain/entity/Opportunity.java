package com.education.uopp.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Opportunity {
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
}
