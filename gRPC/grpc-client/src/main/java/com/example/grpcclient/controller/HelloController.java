package com.example.grpcclient.controller;

import com.example.grpcclient.client.HelloGrpcClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    private final HelloGrpcClient helloGrpcClient;

    public HelloController(HelloGrpcClient helloGrpcClient) {
        this.helloGrpcClient = helloGrpcClient;
    }

    @GetMapping("/talkie")
    public String talkie() {
        return helloGrpcClient.talkie();
    }
}
