package com.example.java_pos.Repo;

import com.example.java_pos.Models.Product;
import com.example.java_pos.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductRepo implements IProductRepo{

    List<Product> products;

    public ProductRepo() {

        products = new ArrayList<>();

        products.add(new Product(1, 1, 5, R.drawable.baseline_account_circle_24, 150.00, "product 1", "desc 1"));
        products.add(new Product(2, 2, 5, R.drawable.baseline_account_circle_24, 150.00, "product 2", "desc 2"));
        products.add(new Product(3, 3, 5, R.drawable.baseline_account_circle_24, 150.00, "product 3", "desc 3"));
        products.add(new Product(4, 4, 5, R.drawable.baseline_account_circle_24, 150.00, "product 4", "desc 4"));
        products.add(new Product(1, 5, 10, R.drawable.baseline_account_circle_24, 150.00, "product 5", "desc 5"));
    }

    @Override
    public List<Product> getAllProducts() {
        return Collections.unmodifiableList(products);
    }

    @Override
    public List<Product> getAllProductsByCategoryId(int categoryId) {

        List<Product> filteredProducts = new ArrayList<>();

        for(Product product : products) {
            if(product.getCategoryId() == categoryId) {
                filteredProducts.add(product);
            }
        }

        return filteredProducts;
    }
}
