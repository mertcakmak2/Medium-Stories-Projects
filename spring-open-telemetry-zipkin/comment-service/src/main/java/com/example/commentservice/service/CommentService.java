package com.example.commentservice.service;

import com.example.commentservice.model.Comment;
import io.micrometer.tracing.Tracer;
import io.micrometer.tracing.annotation.NewSpan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    Logger log = LoggerFactory.getLogger(this.getClass());
    private final Tracer tracer;


    private List<Comment> comments = List.of(
            new Comment(1, "nice post 1", 1),
            new Comment(2, "nice post 2", 1),
            new Comment(3, "nice post 3", 1),
            new Comment(4, "nice post 4", 2),
            new Comment(5, "nice post 5", 2),
            new Comment(6, "nice post 6", 3),
            new Comment(7, "nice post 7", 3),
            new Comment(8, "nice post 8", 3),
            new Comment(9, "nice post 9", 4)
    );

    public CommentService(Tracer tracer) {
        this.tracer = tracer;
    }

    @NewSpan(value = "comment-service-findCommentsByPostId-span")
    public List<Comment> findCommentsByPostId(int postId) throws InterruptedException {
        Thread.sleep(500);
        log.info("findCommentsByPostId log signoza gönderildi.");
        return comments.stream().filter(comment -> comment.getPostId() == postId).toList();
    }
}
