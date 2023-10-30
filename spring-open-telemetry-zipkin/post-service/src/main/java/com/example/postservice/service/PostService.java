package com.example.postservice.service;

import com.example.postservice.domain.Post;
import com.example.postservice.feign.CommentFeignClient;
import com.example.postservice.model.CommentResponseModel;
import io.micrometer.tracing.annotation.NewSpan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PostService {

    private final RestTemplate restTemplate;
    private final CommentFeignClient commentFeignClient;

    @Autowired
    public PostService(RestTemplate restTemplate, CommentFeignClient commentFeignClient) {
        this.restTemplate = restTemplate;
        this.commentFeignClient = commentFeignClient;
    }

    @NewSpan(value = "post-service-getPostWithComments-span")
    public Post getPostWithComments(int id) throws InterruptedException {
        Thread.sleep(500);

//        String url = String.format("http://localhost:8082/api/v1/comments?postId=%s", id);

//        ResponseEntity<List<CommentResponseModel>> responseEntity =
//                restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<CommentResponseModel>>() {});
//        List<CommentResponseModel> comments = responseEntity.getBody();

        List<CommentResponseModel> comments = commentFeignClient.findCommentsByPostId(id);

        return new Post(1, "What is the Zipkin?", "Nice tool", comments);
    }
}
