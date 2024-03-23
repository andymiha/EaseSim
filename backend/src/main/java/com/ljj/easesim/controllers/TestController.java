package com.ljj.easesim.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/")
    public String test() {
        // Process form data and perform required action
        return "BIG DATA - I am in TestController";
    }
//
//    @PostMapping("/getTest")
//    public String getTest() {
//        return "hello world";
//    }
}
