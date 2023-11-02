package com.example.retryable.controller;

import com.example.retryable.exception.SaveTodoException;
import com.example.retryable.domain.Todo;
import com.example.retryable.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("")
    public String saveTodo(@RequestBody Todo todo) throws SaveTodoException, IllegalArgumentException {
        return todoService.saveTodo(todo);
    }
}
