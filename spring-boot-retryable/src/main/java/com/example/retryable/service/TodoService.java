package com.example.retryable.service;

import com.example.retryable.exception.SaveTodoException;
import com.example.retryable.domain.Todo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TodoService {

    @Retryable(retryFor = { SaveTodoException.class, IllegalArgumentException.class }, maxAttempts = 3, backoff = @Backoff(delay = 800))
    public String saveTodo(Todo todo) throws SaveTodoException, IllegalArgumentException {

        if (todo.getContent().equals("SaveTodoException")) {
            log.error("Error when todo saving. [SaveTodoException]");
            throw new SaveTodoException("Todo did not save");
        } else if (todo.getContent().equals("IllegalArgumentException")) {
            log.error("Error when todo saving. [IllegalArgumentException]");
            throw new IllegalArgumentException("Todo did not save");
        }

        return "todo saved.";
    }

    @Recover
    public String recoverSaveTodo(SaveTodoException e, Todo todo){
        log.info(todo.getContent());
        return "recover save todo [SaveTodoException]";
    }

    @Recover
    public String recoverSaveTodo(IllegalArgumentException e, Todo todo){
        log.info(todo.getContent());
        return "recover save todo [IllegalArgumentException]";
    }
}
