package com.education.uopp.controller;

import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * Just to understand the flow
 * TODO: Delete this test class
 */
@RestController
@AllArgsConstructor
public class OpportunityResource {

    @GetMapping("/get")
    public Object getOpportunity(@RequestParam String url) {

//        RestTemplate restTemplate = new RestTemplate();
//        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
//        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
//        messageConverters.add(converter);
//        restTemplate.setMessageConverters(messageConverters);
//        HttpHeaders headers = new HttpHeaders();
//        ParameterizedTypeReference<HashMap<Integer, String>> responseType =
//                new ParameterizedTypeReference<>() {
//                };
//        RequestEntity<Void> request = RequestEntity.get("http://localhost:105/getOpportunities/")
//                .accept(MediaType.APPLICATION_JSON).build();
//        Map<Integer, String> jsonDictionary = restTemplate.exchange(request, responseType).getBody();
//        return jsonDictionary;
        return url;
    }
}
