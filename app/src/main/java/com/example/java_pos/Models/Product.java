package com.example.java_pos.Models;


import java.io.Serializable;

public class Product implements Serializable {

    int imageId;
    String name;
    String desc;
    double price;
    int quantity;
    int productId;

    String type;

    public Product(int imageId, String name, String desc, double price, int quantity, int productId, String type) {
        this.imageId = imageId;
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.quantity = quantity;
        this.productId = productId;
        this.type = type;
    }

    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getProductId() {
        return productId;
    }

    public String getType() {return type;}
}
