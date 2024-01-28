package com.example.springargocdapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HelloController {

    @GetMapping(path = "/hello")
    public String sayHello() {
        return "hello from spring argo cd app";
    }
}
