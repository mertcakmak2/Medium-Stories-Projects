package com.example.postservice.controller;

import com.example.postservice.model.Post;
import com.example.postservice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("")
    public List<Post> findAllPosts() throws InterruptedException {
        return postService.findAllPost();
    }

    @GetMapping(path = "/{id}")
    public Post findPostByIdWithComments(@PathVariable int id) throws InterruptedException {
        return postService.findPostByIdWithComments(id);
    }

}
