package com.example.rediscluster.service;

import com.example.rediscluster.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class CacheService {

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public CacheService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public User cacheUser(User user){
        redisTemplate.opsForValue().set(user.getName(), user, Duration.ofMinutes(2L));
        return user;
    }

    public User getCachedUserByName(String name){
        var user = redisTemplate.opsForValue().get(name);
        return (User) user;
    }
}
