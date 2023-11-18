package com.example.vthreadservice;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import java.time.Duration;
import java.time.Instant;

@SpringBootApplication
public class VThreadServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(VThreadServiceApplication.class, args);
    }

    @Bean
    public ApplicationRunner runner() {

        return args -> {
            var startDate = Instant.now();

//            startThreads();
            startVirtualThreads();

            var finishDate = Instant.now();
            System.out.println(String.format("Start Date: %s, Finish Date: %s", startDate, finishDate));
            System.out.println(String.format("Duration Time(Milliseconds): %s", Duration.between(startDate, finishDate).toMillis()));

        };
    }

    private void startVirtualThreads() throws InterruptedException {

        for (int i = 0; i < 100_000; i++) {
            int finalI = i;
            Thread t = Thread.ofVirtual()
                    .name(String.format("virtualThread-%s", i))
                    .unstarted(() -> System.out.println(finalI));
            t.start();
            t.join();

        }
    }

    private void startThreads() throws InterruptedException {

        for (int i = 0; i < 100_000; i++) {
            int finalI = i;
            Thread t = new Thread(() -> System.out.println(finalI));
            t.start();
            t.join();
        }
    }

}
