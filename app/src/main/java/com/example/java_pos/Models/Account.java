package com.example.java_pos.Models;

public class Account {
    private String username;
    private String password;
    private boolean isLoggedIn;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.isLoggedIn = false;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }
}