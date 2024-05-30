package com.example.java_pos.Models;

public class Category {

    String name;
    int id, image;

    public Category(String name, int id, int image) {
        this.name = name;
        this.id = id;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getImage() {
        return image;
    }
}
