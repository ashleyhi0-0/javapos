package com.example.java_pos.Models;

public class Product {

    int categoryId, productId, quantity, productImg;
    double price;

    String productName, productDesc;

    public Product(int categoryId, int productId, int quantity, int productImg, double price, String productName, String productDesc) {
        this.categoryId = categoryId;
        this.productId = productId;
        this.quantity = quantity;
        this.productImg = productImg;
        this.price = price;
        this.productName = productName;
        this.productDesc = productDesc;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getProductImg() {
        return productImg;
    }

    public double getPrice() {
        return price;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDesc() {
        return productDesc;
    }
}
