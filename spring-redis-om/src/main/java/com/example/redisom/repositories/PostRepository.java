package com.example.redisom.repositories;

import com.example.redisom.models.Post;
import com.redis.om.spring.repository.RedisDocumentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Set;

@Repository
public interface PostRepository extends RedisDocumentRepository<Post, String> {

    Iterable<Post> findByClapCountBetween(int minClap, int maxClap);
//    Page<Post> findByClapCountBetween(int minClap, int maxClap, Pageable  pageable);

    Iterable<Post> findByAuthor_NameAndAndAuthor_LastName(String name, String lastName);

    // Full text search
    Iterable<Post> searchByContent(String text);

    Iterable<Post> findByComments_Content(String content);

}
