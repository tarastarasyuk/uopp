package com.education.uopp.springcourse.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @ApiOperation(value = "Root controller", notes = "Just print default message")

    @GetMapping("/")
    public String helloWorld() {
        return "Hello World!";
    }
}
