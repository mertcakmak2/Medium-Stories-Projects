package com.example.springredisdistributedlock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringRedisDistributedLockApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringRedisDistributedLockApplication.class, args);
    }

}
