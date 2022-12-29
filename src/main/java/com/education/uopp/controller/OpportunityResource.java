package com.education.uopp.controller;

import com.education.uopp.integration.feign.OpportunityFromTelegramClient;
import com.education.uopp.springcourse.dto.OpportunityDto;
import com.education.uopp.springcourse.model.SCOpportunity;
import com.education.uopp.springcourse.service.OpportunityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class OpportunityResource {

    private final OpportunityFromTelegramClient feignClient;
    private final OpportunityService opportunityService;

    @GetMapping("/uploadOpportunites")
    public ResponseEntity<List<SCOpportunity>> getOpportunity(@PathVariable String channelName, @PathVariable Integer q) {
        List<OpportunityDto> opportunityDtoList = feignClient.getOpportunities(channelName, q);

        List<SCOpportunity> opportunityList = opportunityDtoList.stream()
                .map(OpportunityDto::toOpportunity).toList();

        opportunityList.forEach(opportunityService::create);
        return new ResponseEntity<>(opportunityList, HttpStatus.OK);
    }
}
