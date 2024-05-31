package com.example.java_pos.Models;

public class Product {
    private int imageId;

    private String description;
    private String name;
    private double price;
    private int quantity;

    private int productId;



    public Product(int imageId, String name, String description, double price, int quantity, int productId) {
        this.imageId = imageId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.productId = productId;
        this.description = description;
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

    public int getProductId() { return productId; }

    public String getDescription() {return description; }
}
