package com.example.commentservice.model;

public class Comment {

    private int id;
    private String content;
    private int postId;

    public Comment(int id, String content, int postId) {
        this.id = id;
        this.content = content;
        this.postId = postId;
    }

    public Comment() {
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
