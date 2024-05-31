package com.example.java_pos.Views;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.java_pos.Models.Product;
import com.example.java_pos.Repo.Adapter.ProductAdapter;
import com.example.java_pos.Repo.ProductRepo;

import java.util.List;
import com.example.java_pos.R;

public class ProductActivity extends AppCompatActivity {

    List<Product> products;

    RecyclerView recyclerView;

    ProductAdapter productAdapter;

    ProductRepo productRepo;

    int categoryId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_activity);

        categoryId = getIntent().getIntExtra("categoryId", -1);

        productRepo = new ProductRepo();

        productList();
    }

    private void productList() {

        recyclerView = findViewById(R.id.productRV);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        fetchAndDisplayProducts();
    }

    private void fetchAndDisplayProducts() {

        products = productRepo.getAllProductsByCategoryId(categoryId);

        productAdapter = new ProductAdapter(products, this);

        recyclerView.setAdapter(productAdapter);
    }
}
