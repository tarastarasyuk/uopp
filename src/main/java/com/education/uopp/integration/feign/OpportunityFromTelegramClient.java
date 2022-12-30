package com.education.uopp.integration.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "opportunityBase", url = "${fc.opportunity.url}")
public interface OpportunityFromTelegramClient {
    @GetMapping(value = "/getOpportunities")
    List<OpportunityFeignDto> getOpportunities(
            @RequestParam(value = "channel", required = false) String channel,
            @RequestParam(value = "q", required = false) Integer q
    );
}
