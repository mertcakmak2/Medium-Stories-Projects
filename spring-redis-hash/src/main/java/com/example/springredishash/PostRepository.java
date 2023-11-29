package com.example.springredishash;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<Post, String> {

    List<Post> findByAuthor(String author);

    List<Post> findByTitleContains(String term);

    List<Post> findByClapCountBetween(int min, int max);

    //List<Post> findByTopicIn(String t);
}
