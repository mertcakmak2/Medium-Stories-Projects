package com.example.springtestcontainers.service;

import com.example.springtestcontainers.domain.User;
import com.example.springtestcontainers.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findUserById(String id) {
        return userRepository.findById(id).get();
    }
}
