package com.example.springbootredisearch.domain;

import com.redis.om.spring.repository.RedisDocumentRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends RedisDocumentRepository<Post, String > {

    Iterable<Post> findByTitle(String title);

    Iterable<Post> searchByContent(String content);
}
