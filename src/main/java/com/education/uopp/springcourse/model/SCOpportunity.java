package com.education.uopp.springcourse.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sc_opportunity")
public class SCOpportunity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deadline;

    @Column(nullable = false)
    private Boolean asap;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;

    public SCOpportunity(String name, Date deadline, Boolean asap, String content) {
        this.name = name;
        this.deadline = deadline;
        this.asap = asap;
        this.content = content;
    }
}
