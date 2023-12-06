package com.example.springtestcontainers.service;

import com.example.springtestcontainers.domain.User;

public interface UserService {
    User saveUser(User user);

    User findUserById(String id);
}
