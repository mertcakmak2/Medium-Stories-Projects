package com.example.redisom.controller;

import com.example.redisom.models.Post;
import com.example.redisom.repositories.PostRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping(path = "")
    public Iterable<Post> searchPostByContent(@RequestParam String text) {
        return postRepository.searchByContent(text);
    }
}
