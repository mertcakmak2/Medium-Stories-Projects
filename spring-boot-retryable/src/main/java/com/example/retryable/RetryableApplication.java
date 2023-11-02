package com.example.retryable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class RetryableApplication {

    public static void main(String[] args) {
        SpringApplication.run(RetryableApplication.class, args);
    }

}
