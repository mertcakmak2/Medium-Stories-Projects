package com.example.springawssqs;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringAwsSqsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAwsSqsApplication.class, args);
    }

    @Bean
    public ApplicationRunner runner(Publisher publisher) {
        return args -> {
            publisher.publishMessage();
        };
    }

}
