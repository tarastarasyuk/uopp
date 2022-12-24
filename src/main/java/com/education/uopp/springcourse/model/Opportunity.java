package com.education.uopp.springcourse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Opportunity {
    private Long id;
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deadline;

    private Boolean ASAP;
    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;

    public Opportunity(String name, Date deadline, Boolean ASAP, String content) {
        this.name = name;
        this.deadline = deadline;
        this.ASAP = ASAP;
        this.content = content;
    }
}
