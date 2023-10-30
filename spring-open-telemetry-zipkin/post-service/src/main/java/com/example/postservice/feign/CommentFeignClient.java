package com.example.postservice.feign;


import com.example.postservice.model.CommentResponseModel;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "comment-service", url = "http://localhost:8082/api/v1/comments")
public interface CommentFeignClient {

    @GetMapping("")
    @CircuitBreaker(name = "findCommentsByPostIdCircuitBreaker", fallbackMethod = "findCommentsByPostIdFallback")
    List<CommentResponseModel> findCommentsByPostId(@RequestParam int postId);

    default List<CommentResponseModel> findCommentsByPostIdFallback(Exception exception) {
        System.out.println("circuit breaker default method");
        return List.of();
    }


}

