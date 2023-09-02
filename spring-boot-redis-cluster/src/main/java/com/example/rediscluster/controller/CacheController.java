package com.example.rediscluster.controller;

import com.example.rediscluster.model.User;
import com.example.rediscluster.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users/cache")
public class CacheController {

    private final CacheService cacheService;

    @Autowired
    public CacheController(CacheService cacheService) {
        this.cacheService = cacheService;
    }


    @PostMapping(path = "")
    public User cacheUser(@RequestBody User user) {
      return cacheService.cacheUser(user);
    }

    @GetMapping(path = "/{name}")
    public User getCachedUserByName(@PathVariable String name) {
        return cacheService.getCachedUserByName(name);
    }
}
