package com.example.javaflags;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/health")
public class Controller {

    private List<String> stringList = new ArrayList<>();

    @GetMapping
    public String health() throws InterruptedException {
        Thread.sleep(200);
        var healthString = "health";
        stringList.add(healthString);
        return healthString;
    }

    @GetMapping(path = "/memory-status")
    public MemoryStats getMemoryStatistics() {
        MemoryStats stats = new MemoryStats(
                Runtime.getRuntime().totalMemory(),
                Runtime.getRuntime().maxMemory(),
                Runtime.getRuntime().freeMemory()
        );
        return stats;
    }
}


