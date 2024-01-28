package com.example.springargocdapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class SpringArgoCdApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringArgoCdApplication.class, args);
    }

    @Bean
    ApplicationRunner runner(){
        return args -> {
              log.info("Application Version: V2");
        };
    }

}
