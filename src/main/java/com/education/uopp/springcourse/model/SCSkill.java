package com.education.uopp.springcourse.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sc_skill")
public class SCSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String type;

    public SCSkill(String type) {
        this.type = type;
    }
}
