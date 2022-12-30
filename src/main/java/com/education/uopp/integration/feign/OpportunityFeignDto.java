package com.education.uopp.integration.feign;

import com.education.uopp.springcourse.model.SCOpportunity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class OpportunityFeignDto {
    @JsonProperty("post_id")
    private Long postId;
    private String content;
    @JsonProperty("created_at")
    private Date createdAt;

    public SCOpportunity toOpportunity() {
        return new SCOpportunity(postId, content, createdAt);
    }
}
