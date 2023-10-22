package com.test.kafka.controller;

import com.test.kafka.model.Todo;
import com.test.kafka.producer.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/todos")
@RequiredArgsConstructor
public class TodoController {

    private final KafkaProducer kafkaProducer;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public Todo createTodo() {
        int randomWithMathRandom = (int) ((Math.random() * (100 - 1)) + 1);

        var todo = Todo.builder()
                .id(randomWithMathRandom)
                .todo("todo description")
                .build();
        kafkaProducer.sendTodoToKafka(todo);
        return todo;
    }

}
