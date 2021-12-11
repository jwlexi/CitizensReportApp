package com.example.citizensreportapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {
    private String username, password;

    private List<User> data;

    public List<User> getData() {
        return data;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return  "Username: " + username + "\nPassword: " + password;
    }
}
