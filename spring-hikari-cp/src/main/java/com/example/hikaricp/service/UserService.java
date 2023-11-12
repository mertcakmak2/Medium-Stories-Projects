package com.example.hikaricp.service;

import com.example.hikaricp.model.User;
import com.example.hikaricp.repository.UserRepository;
import io.micrometer.tracing.annotation.NewSpan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @NewSpan(value = "user-service-findAllUser-method-span")
    @Transactional
    public List<User> findAllUsers() throws InterruptedException {
        Thread.sleep(1000);
        //log.info("find all users...");
        return userRepository.findAll();
    }

}
