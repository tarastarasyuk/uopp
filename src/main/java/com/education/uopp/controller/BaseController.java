package com.education.uopp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

    @GetMapping("/hello")
    public String index() {
        return "hello";
    }
}
