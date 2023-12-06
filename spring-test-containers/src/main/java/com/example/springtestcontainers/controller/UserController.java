package com.example.springtestcontainers.controller;

import com.example.springtestcontainers.domain.User;
import com.example.springtestcontainers.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping(path = "/{id}")
    public User findUserById(@PathVariable String id) {
        return userService.findUserById(id);
    }
}
