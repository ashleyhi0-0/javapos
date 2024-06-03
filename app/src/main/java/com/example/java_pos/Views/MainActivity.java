package com.example.java_pos.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

    private Spinner spinnerCategories;

    private RecyclerView recyclerView;

    private Map<String, List<Product>> categoryProductMap;

    private ProductAdapter productAdapter;

    private AccountRepo accountRepo;

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // initialize accountRepo

        accountRepo = new AccountRepo(this);

        // check if user is already logged in.

        if(accountRepo.getLoggedInAccount() == null || !accountRepo.getLoggedInAccount().isLoggedIn()) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        // initialize bottomnavigationview

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavListener);

        // initialize recyclerview & productAdapter
        productAdapter = new ProductAdapter(new ArrayList<>(), this);
        recyclerView = findViewById(R.id.recyclerview_products);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(productAdapter);

        // initialize spinnerCategories

        spinnerCategories = findViewById(R.id.spinner_categories);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new ArrayList<>());
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategories.setAdapter(spinnerAdapter);
        loadProductData();

        spinnerCategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCategories = parent.getItemAtPosition(position).toString();
                List<Product> productList = categoryProductMap.get(selectedCategories);
                productAdapter.updateProductList(productList);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                productAdapter.updateProductList(new ArrayList<>());
            }
        });
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

    static List<Product> generateGroceriesList() {

        List<Product> productList = new ArrayList<>();
        productList.add(new Product(R.drawable.baseline_3p_24, "Apple", "this is an apple", 4.99, 12, 1, "Groceries"));
        return productList;
    }

    static List<Product> generateClothingList() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(R.drawable.baseline_3p_24, "Tshirt", "this is a tshirt", 14.99, 12, 2, "Clothings"));
        return productList;
    }

    static List<Product> generateElectronicsList() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(R.drawable.baseline_3p_24, "Laptop", "this is a laptop", 54.99, 12, 3, "Electronics"));
        return productList;
    }

    public BottomNavigationView.OnNavigationItemSelectedListener bottomNavListener = item -> {

        int id = item.getItemId();

        if(id == R.id.home) {
            return true;
        }else if(id == R.id.shop) {
            startActivity(new Intent(MainActivity.this, CheckoutActivity.class));
            overridePendingTransition(R.anim.slide_in_right, 0);
            return true;
        }else if(id == R.id.profile) {
            startActivity(new Intent(MainActivity.this, ProfileActivity.class));
            overridePendingTransition(R.anim.slide_in_right, 0);
            return true;
        }

        return false;
    };
}
