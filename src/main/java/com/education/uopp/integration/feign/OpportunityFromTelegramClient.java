package com.education.uopp.integration.feign;

import com.education.uopp.springcourse.dto.OpportunityDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

// TODO: url to properties
@FeignClient(value = "opportunityBase", url = "http://localhost:105/getOpportunities")
public interface OpportunityFromTelegramClient {

    @RequestMapping(method = RequestMethod.GET, value = "/teleopp")
    List<OpportunityDto> getOpportunities(@PathVariable("channel") String channel, @PathVariable("q") Integer q);

}
