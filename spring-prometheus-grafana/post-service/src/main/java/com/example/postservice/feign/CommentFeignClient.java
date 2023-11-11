package com.example.postservice.feign;

import com.example.postservice.model.Comment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "comment-service", url = "http://localhost:8082/api/v1/comments")
public interface CommentFeignClient {

    @GetMapping("")
    List<Comment> findCommentsByPostId(@RequestParam int postId);

}
