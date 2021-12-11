package com.example.citizensreportapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Post {
    private int userId;
    private Integer id;
    private String title, text;

//    public Post(int userId, String title, String text) {
//        this.userId = userId;
//        this.title = title;
//        this.text = text;
//    }
    private List<Post> data;

    public List<Post> getData() {
        return data;
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return  "Title: " + title + "\nText: " + text;
    }
}
