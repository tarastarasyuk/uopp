package com.education.uopp.springcourse.dto;

import com.education.uopp.springcourse.model.Opportunity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class OpportunityDto {
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date deadline;
    private Boolean asap;
    private String content;

    public Opportunity toOpportunity() {
        return new Opportunity(null, name, deadline, asap, content, null);
    }
}
