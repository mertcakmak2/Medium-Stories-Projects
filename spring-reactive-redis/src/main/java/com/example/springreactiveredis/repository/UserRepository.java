package com.example.springreactiveredis.repository;

import com.example.springreactiveredis.domin.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.UUID;

@Repository
public class UserRepository {

    private final ReactiveRedisOperations<String, User> reactiveRedisOperations;

    @Autowired
    public UserRepository(ReactiveRedisOperations<String, User> reactiveRedisOperations) {
        this.reactiveRedisOperations = reactiveRedisOperations;
    }

    public Flux<User> findAllUsers() {
        return this.reactiveRedisOperations.opsForList().range("users", 0, -1);
    }

    public Mono<User> findUserById(String id) {
        return this.findAllUsers().filter(p -> p.getId().equals(id))
                .switchIfEmpty(Mono.just(User.builder().build()))
                .last();
    }

    public Mono<String> saveUser(User user) {
        user.setId(UUID.randomUUID().toString());

//        Duration ttl = Duration.ofSeconds(60); // Set TTL to 60 seconds
//        reactiveRedisOperations.opsForHash()
//                .put(user.getId(), user.getId(), user)
//                .flatMap(result -> reactiveRedisOperations.expire(user.getId(), ttl))
//                .map(result -> "Key set with TTL").subscribe();

        String key = "users";
        Duration ttl = Duration.ofSeconds(60); // Set TTL to 60 seconds

        return reactiveRedisOperations.opsForList()
                .rightPush(key, user)
                .flatMap(result -> reactiveRedisOperations.expire(key, ttl))
                .map(result -> "List set with TTL");

    }

    public Mono<Boolean> deleteAllUsers() {
        return this.reactiveRedisOperations.opsForList().delete("users");
    }

    public Mono<Long> publishUser(User user) {
        user.setId(UUID.randomUUID().toString());
        return this.reactiveRedisOperations.convertAndSend("users", user);
    }

}
