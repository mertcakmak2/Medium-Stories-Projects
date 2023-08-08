package com.medium.githubactions.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@RestController
@RequestMapping("/api/v1")
public class GreetingController {

    @GetMapping(path = "")
    public String greeting() {
        return "Greetings from Spring App";
    }

}
