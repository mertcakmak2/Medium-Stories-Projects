package com.example.postservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class Post {

    private int id;

    private String title;

    private String content;

    private List<Comment> comments = new ArrayList<>();
}
