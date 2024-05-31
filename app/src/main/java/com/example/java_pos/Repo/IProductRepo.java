package com.example.java_pos.Repo;

import com.example.java_pos.Models.Product;

import java.util.List;

public interface IProductRepo {

    List<Product> getAllProducts();

    List<Product> getAllProductsByCategoryId(int categoryId);

}
