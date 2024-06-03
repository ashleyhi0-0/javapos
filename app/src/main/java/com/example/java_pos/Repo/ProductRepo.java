package com.example.java_pos.Repo;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.java_pos.Models.Product;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductRepo {

    private static final String PREFS_NAME = "product_prefs";
    private static final String PRODUCTS_KEY = "products_key";
    private SharedPreferences sharedPreferences;
    private Gson gson;

    public ProductRepo(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        this.gson = new Gson();
    }

    public Map<Integer, Product> getAllProducts() {
        String json = sharedPreferences.getString(PRODUCTS_KEY, "");
        Type type = new TypeToken<HashMap<Integer, Product>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public Product getProductById(int productId) {
        Map<Integer, Product> products = getAllProducts();
        return products != null ? products.get(productId) : null;
    }

    public void updateProduct(Product product) {
        Map<Integer, Product> products = getAllProducts();
        if (products != null) {
            products.put(product.getProductId(), product);
            saveAllProducts(products);
        }
    }

    private void saveAllProducts(Map<Integer, Product> products) {
        String json = gson.toJson(products);
        sharedPreferences.edit().putString(PRODUCTS_KEY, json).apply();
    }
}
