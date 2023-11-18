package com.example.vthreadservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/threads")
@Slf4j
@EnableAsync
public class ThreadController {

    @GetMapping("")
    public String thread() throws InterruptedException {
        Thread.sleep(1000);
        var threadName = Thread.currentThread().toString();
        log.info(threadName);
        return "thread executed";
    }

}
