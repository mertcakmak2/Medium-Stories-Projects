package com.example.webflux.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorResponse extends Response {

    private String message;

    public ErrorResponse(String message) {
        super(false);
        this.message = message;
    }
}
