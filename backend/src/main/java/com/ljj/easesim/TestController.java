package com.ljj.easesim;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/")
    public String hello() {
        return "hello";
    }

    @PostMapping("/getTest")
    public String getTest() {
        return "hello world";
    }
}
