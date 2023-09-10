package com.example.webflux.service;

import com.example.webflux.domain.Todo;
import com.example.webflux.exception.TodoNotFoundException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.ExecutionException;

public interface TodoService {
    Mono<Todo> findTodoById(int id);
    Mono<Todo> searchTodoByTitle(String title);
    Flux<Todo> findAllTodos();
    Mono<Todo> saveTodo(Todo todo);
    Mono<Void> deleteTodoById(int id);
}
