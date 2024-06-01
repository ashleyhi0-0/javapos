package com.example.java_pos.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.java_pos.Models.Product;
import com.example.java_pos.R;
import com.example.java_pos.Repo.AccountRepo;
import com.example.java_pos.Repo.Adapter.ProductAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    private Spinner spinnerCategories;
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private Map<String, List<Product>> categoryProductMap;

    private AccountRepo accountRepo;

    private Button checkOutBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        accountRepo = new AccountRepo(this);

        // Check if the user is logged in
        if (accountRepo.getLoggedInAccount() == null || !accountRepo.getLoggedInAccount().isLoggedIn()) {
            // If not logged in, redirect to LoginActivity
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        initializeUI();
        setupRecyclerView();
        setupSpinner();
        loadProductData();
        setupSpinnerListener();

        // Initialize BottomNavigationView
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(navItemSelectedListener);

        // Set default selected item
        bottomNavigationView.setSelectedItemId(R.id.home);
    }

    private void initializeUI() {
        spinnerCategories = findViewById(R.id.spinner_categories);
        recyclerView = findViewById(R.id.recyclerview_products);

    }

    private void setupRecyclerView() {
        productAdapter = new ProductAdapter(new ArrayList<>(), this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(productAdapter);
    }

    private void setupSpinner() {
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new ArrayList<>());
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategories.setAdapter(spinnerAdapter);
    }

    private void loadProductData() {
        categoryProductMap = new HashMap<>();
        categoryProductMap.put("Groceries", generateGroceriesList());
        categoryProductMap.put("Clothings", generateClothingList());
        categoryProductMap.put("Electronics", generateElectronicsList());

        ArrayAdapter<String> spinnerAdapter = (ArrayAdapter<String>) spinnerCategories.getAdapter();
        spinnerAdapter.clear();
        spinnerAdapter.addAll(categoryProductMap.keySet());
        spinnerAdapter.notifyDataSetChanged();
    }

    private void setupSpinnerListener() {
        spinnerCategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCategory = parent.getItemAtPosition(position).toString();
                List<Product> productList = categoryProductMap.get(selectedCategory);
                productAdapter.updateProductList(productList);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                productAdapter.updateProductList(new ArrayList<>());
            }
        });
    }

    static List<Product> generateGroceriesList() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(R.drawable.baseline_3p_24, "Apple", "This is an apple", 129.99, 12, 1, "Groceries"));
        // Add more products as needed
        return productList;
    }

    static List<Product> generateClothingList() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(R.drawable.baseline_3p_24, "Shirt", "This is a shirt", 49.99, 20, 2, "Clothings"));
        // Add more products as needed
        return productList;
    }

    static List<Product> generateElectronicsList() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(R.drawable.baseline_3p_24, "Laptop", "This is a laptop", 999.99, 5, 3, "Electronics"));
        // Add more products as needed
        return productList;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navItemSelectedListener = item -> {

        int id = item.getItemId();

        if (id == R.id.home) {
            return true;
        }
        else if(id == R.id.shop) {
            startActivity(new Intent(MainActivity.this, CheckoutActivity.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // Custom animations
            return true;
        }
        else if(id == R.id.profile) {
            return true;
        }
        return false;
    };
}
