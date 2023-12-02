package com.example.javaflags;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/health")
public class Controller {

    @GetMapping
    public String health() {
        return "health";
    }
}
