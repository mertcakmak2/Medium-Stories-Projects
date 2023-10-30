package com.example.postservice.domain;

import com.example.postservice.model.CommentResponseModel;

import java.util.ArrayList;
import java.util.List;

public class Post {

    private int id;
    private String title;
    private String content;
    private List<CommentResponseModel> commentResponseModels = new ArrayList<>();

    public Post(int id, String title, String content, List<CommentResponseModel> commentResponseModels) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.commentResponseModels = commentResponseModels;
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
        return commentResponseModels;
    }

    public void setComments(List<CommentResponseModel> commentResponseModels) {
        this.commentResponseModels = commentResponseModels;
    }
}
