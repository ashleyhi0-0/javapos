package com.example.java_pos.Models;

public class Product {
    private int imageId;
    private String name;
    private double price;
    private int quantity;

    public Product(int imageId, String name, double price, int quantity) {
        this.imageId = imageId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
