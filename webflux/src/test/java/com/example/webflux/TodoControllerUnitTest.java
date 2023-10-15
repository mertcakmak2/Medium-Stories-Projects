package com.example.webflux;

import com.example.webflux.controller.TodoController;
import com.example.webflux.domain.Todo;
import com.example.webflux.exception.TodoNotFoundException;
import com.example.webflux.response.ErrorResponse;
import com.example.webflux.service.TodoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@WebFluxTest(controllers = TodoController.class)
@AutoConfigureWebTestClient
public class TodoControllerUnitTest {

    private final WebTestClient webTestClient;

    @MockBean
    private final TodoService todoService;

    private final String url = "/api/v1/todos";

    @Autowired
    public TodoControllerUnitTest(WebTestClient webTestClient, TodoService todoService) {
        this.webTestClient = webTestClient;
        this.todoService = todoService;
    }

    @Test
    void findTodoByIdTest() {
        var todo = Todo.builder().id(1).title("test title 1").description("test description 1").build();

        when(todoService.findTodoById(todo.getId())).thenReturn(Mono.just(todo));

        String findTodoByIdUrl = String.format("%s/%s", url, todo.getId());
        webTestClient
                .get()
                .uri(findTodoByIdUrl)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(Todo.class)
                .consumeWith(result -> {
                    var existTodo = result.getResponseBody();

                    assert existTodo != null;
                    assertEquals(todo.getId(), existTodo.getId());
                    assertEquals(todo.getTitle(), existTodo.getTitle());

                });
    }

    @Test
    void findTodoByIdFailureTest() {
        var todo = Todo.builder().id(3).title("test title 3").description("test description 3").build();

        when(todoService.findTodoById(todo.getId())).thenReturn(Mono.error(new TodoNotFoundException(String.format("Todo not found. ID: %s", todo.getId()))));

        String findTodoByIdUrl = String.format("%s/%s", url, todo.getId());
        webTestClient
                .get()
                .uri(findTodoByIdUrl)
                .exchange()
                .expectStatus()
                .isNotFound()
                .expectBody(ErrorResponse.class)
                .consumeWith(result -> {
                    var errorResponse = result.getResponseBody();

                    assertEquals(false, errorResponse.isSuccess());
                    assertEquals("Todo not found. ID: 3", errorResponse.getMessage());
                });
    }

    @Test
    void findAllTodosTest() {
        var todos = List.of(
                Todo.builder().id(1).title("test title 1").description("test description 1").build(),
                Todo.builder().id(2).title("test title 2").description("test description 2").build(),
                Todo.builder().id(3).title("test title 3").description("test description 3").build()
        );

        when(todoService.findAllTodos()).thenReturn(Flux.fromIterable(todos));

        webTestClient
                .get()
                .uri(url)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBodyList(Todo.class)
                .hasSize(3);
    }

    @Test
    void saveTodoTest() {
        var todo = Todo.builder().id(1).title("test title 1").description("test description 1").build();

        when(todoService.saveTodo(isA(Todo.class))).thenReturn(Mono.just(todo));

        webTestClient
                .post()
                .uri(url)
                .bodyValue(todo)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(Todo.class)
                .consumeWith(result -> {
                    var savedTodo = result.getResponseBody();

                    assert savedTodo != null;
                    assertEquals(todo.getId(), savedTodo.getId());
                    assertEquals(todo.getTitle(), savedTodo.getTitle());

                });
    }

    @Test
    void deleteTodoById() {
        int todoId = 3;

        when(todoService.deleteTodoById(todoId)).thenReturn(Mono.empty());

        String deleteUrl = String.format("%s/%s", url, todoId);
        webTestClient
                .delete()
                .uri(deleteUrl)
                .exchange()
                .expectStatus()
                .isNoContent();
    }

}
