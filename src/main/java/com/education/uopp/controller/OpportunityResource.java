package com.education.uopp.controller;

import com.education.uopp.integration.feign.OpportunityFeignDto;
import com.education.uopp.integration.feign.OpportunityFromTelegramClient;
import com.education.uopp.springcourse.model.SCOpportunity;
import com.education.uopp.springcourse.service.OpportunityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class OpportunityResource {

    private final OpportunityFromTelegramClient feignClient;
    private final OpportunityService opportunityService;

    @GetMapping("/uploadOpportunities")
    public ResponseEntity<List<SCOpportunity>> uploadOpportunities(
            @RequestParam(value = "channel", required = false) String channel,
            @RequestParam(value = "q", required = false) Integer q
    ) {
        List<OpportunityFeignDto> opportunityDtoList = feignClient.getOpportunities(channel, q);

        List<SCOpportunity> opportunityList = opportunityDtoList.stream()
                .filter(ofd -> !opportunityService.existsByPostIdAndPostIdIsNotNull(ofd.getPostId()))
                .map(OpportunityFeignDto::toOpportunity).toList();

        opportunityList.forEach(opportunityService::create);
        return new ResponseEntity<>(opportunityList, HttpStatus.OK);
    }
}
