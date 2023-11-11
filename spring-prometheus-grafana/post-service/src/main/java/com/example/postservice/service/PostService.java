package com.example.postservice.service;

import com.example.postservice.feign.CommentFeignClient;
import com.example.postservice.model.Comment;
import com.example.postservice.model.Post;
import io.micrometer.tracing.annotation.NewSpan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PostService {

    private final CommentFeignClient commentFeignClient;

    @Autowired
    public PostService(CommentFeignClient commentFeignClient) {
        this.commentFeignClient = commentFeignClient;
    }

    @NewSpan(value = "post-service-findAllPost-method-span")
    public List<Post> findAllPost() throws InterruptedException {
        Thread.sleep(1000);
        //log.info("find all posts...");
        return List.of( new Post(1, "What is the Prometheus?", "Nice tool", null));
    }

    @NewSpan(value = "post-service-getPostWithComments-span")
    public Post findPostByIdWithComments(int id) {
        List<Comment> comments = commentFeignClient.findCommentsByPostId(id);
        return new Post(1, "What is the Prometheus?", "Nice tool", comments);
    }
}
