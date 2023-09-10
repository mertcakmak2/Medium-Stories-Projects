package com.example.webflux.exception;

import com.example.webflux.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(TodoNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Mono<ErrorResponse> handleTodoNotFoundException(TodoNotFoundException exception) {
        Mono<ErrorResponse> errResponse = Mono.just(new ErrorResponse(exception.getMessage()));
        return errResponse;
    }
}
