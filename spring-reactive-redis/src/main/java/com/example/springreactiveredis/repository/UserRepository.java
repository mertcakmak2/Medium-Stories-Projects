package com.example.springreactiveredis.repository;

import com.example.springreactiveredis.domin.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public class UserRepository {

    private final ReactiveRedisOperations<String, User> reactiveRedisOperations;

    @Autowired
    public UserRepository(ReactiveRedisOperations<String, User> reactiveRedisOperations) {
        this.reactiveRedisOperations = reactiveRedisOperations;
    }

    public Flux<User> findAllUsers(){
        return this.reactiveRedisOperations.opsForList().range("users", 0, -1);
    }

    public Mono<User> findUserById(String id) {
        return this.findAllUsers().filter(p -> p.getId().equals(id))
                .switchIfEmpty(Mono.just(User.builder().build()))
                .last();
    }

    public Mono<Long> saveUser(User user){
        user.setId(UUID.randomUUID().toString());
        return this.reactiveRedisOperations.opsForList().rightPush("users", user);
    }

    public Mono<Boolean> deleteAllUsers() {
        return this.reactiveRedisOperations.opsForList().delete("users");
    }

    public Mono<Long> publishUser(User user) {
        user.setId(UUID.randomUUID().toString());
        return this.reactiveRedisOperations.convertAndSend("users", user);
    }

}
