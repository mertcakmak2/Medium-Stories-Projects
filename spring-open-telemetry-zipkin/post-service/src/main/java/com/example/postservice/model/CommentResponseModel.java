package com.example.postservice.model;

public class CommentResponseModel {

    private int id;
    private String content;
    private int postId;

    public CommentResponseModel(int id, String content, int postId) {
        this.id = id;
        this.content = content;
        this.postId = postId;
    }

    public CommentResponseModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }
}
