package com.example.postservice.domain;

import com.example.postservice.model.CommentResponseModel;

import java.util.ArrayList;
import java.util.List;

public class Post {

    private int id;
    private String title;
    private String content;
    private List<CommentResponseModel> comments = new ArrayList<>();

    public Post(int id, String title, String content, List<CommentResponseModel> comments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.comments = comments;
    }

    public Post() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<CommentResponseModel> getComments() {
        return comments;
    }

    public void setComments(List<CommentResponseModel> comments) {
        this.comments = comments;
    }
}
