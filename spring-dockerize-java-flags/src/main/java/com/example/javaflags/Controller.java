package com.example.javaflags;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class Controller {

    private List<String> list = new ArrayList<>();

    @GetMapping(path = "/fill")
    public String fillTheList() throws InterruptedException {
        Thread.sleep(200);
        var filled = "filled";
        list.add(filled);
        return filled;
    }

    @GetMapping(path = "/memory-status")
    public MemoryStats getMemoryStatistics() {
        return new MemoryStats(
                Runtime.getRuntime().totalMemory(),
                Runtime.getRuntime().maxMemory(),
                Runtime.getRuntime().freeMemory()
        );
    }
}


