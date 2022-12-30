package com.education.uopp.springcourse.dto;

import com.education.uopp.springcourse.model.SCOpportunity;
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

    public SCOpportunity toOpportunity() {
        return new SCOpportunity(name, deadline, asap, content);
    }
}
