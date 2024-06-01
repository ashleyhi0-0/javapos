package com.example.java_pos.Models;

import java.io.Serializable;

public class Order implements Serializable {
    private String productName;
    private int quantity;
    private double price;

    // Constructor
    public Order(String productName, int quantity, double price) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters
    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
}
