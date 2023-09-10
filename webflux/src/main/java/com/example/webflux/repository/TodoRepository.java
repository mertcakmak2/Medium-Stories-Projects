package com.example.webflux.repository;

import com.example.webflux.domain.Todo;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface TodoRepository extends ReactiveCrudRepository<Todo,Integer> {

    Mono<Todo> findTodoByTitle(String title);
}
