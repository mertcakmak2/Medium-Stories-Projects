package com.example.springreactiveredis.controller;

import com.example.springreactiveredis.domin.User;
import com.example.springreactiveredis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping(path = "")
    public Mono<String> saveUser(@RequestBody User user) {
        return userRepository.saveUser(user);
    }

    @GetMapping(path = "/{id}")
    public Mono<User> findUserById(@PathVariable String id) {
        return userRepository.findUserById(id);
    }

    @GetMapping(path = "")
    public Flux<User> findAllUsers() {
        return userRepository.findAllUsers();
    }

    @PostMapping(path = "/publish")
    public Mono<Long> publishUser(@RequestBody User user) {
        return userRepository.publishUser(user);
    }

    @DeleteMapping(path = "")
    public Mono<Boolean> deleteAllUsers() {
        return userRepository.deleteAllUsers();
    }

}
