package com.example.java_pos.Views;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.java_pos.Models.Product;
import com.example.java_pos.R;
import com.example.java_pos.Repo.Adapter.ProductAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerCategories;
    private RecyclerView recyclerViewProducts;
    private ProductAdapter productAdapter;
    private Map<String, List<Product>> categoryProductMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerCategories = findViewById(R.id.spinner_categories);
        recyclerViewProducts = findViewById(R.id.recyclerview_products);

        // Sample data
        categoryProductMap = new HashMap<>();
        categoryProductMap.put("Electronics", generateElectronicsProductList());
        categoryProductMap.put("Groceries", generateGroceriesProductList());
        categoryProductMap.put("Clothing", generateClothingProductList());

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new ArrayList<>(categoryProductMap.keySet()));
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategories.setAdapter(spinnerAdapter);

        productAdapter = new ProductAdapter(new ArrayList<>());
        recyclerViewProducts.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false));
        recyclerViewProducts.setAdapter(productAdapter);

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

    private List<Product> generateElectronicsProductList() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(R.drawable.baseline_account_circle_24, "Apple Watch", "description 1", 399.99, 10, 1));
        productList.add(new Product(R.drawable.baseline_account_circle_24, "Laptop", "description 2",999.99, 5, 2));
        productList.add(new Product(R.drawable.baseline_account_circle_24, "Smartphone", "description 3",699.99, 8, 3));
        return productList;
    }

    private List<Product> generateGroceriesProductList() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(R.drawable.baseline_account_circle_24, "Apple", "description 4",0.99, 50, 4));
        productList.add(new Product(R.drawable.baseline_account_circle_24, "Banana", "description 5",0.49, 100, 5));
        productList.add(new Product(R.drawable.baseline_account_circle_24, "Bread", "description 6",1.99, 20, 6));
        return productList;
    }

    private List<Product> generateClothingProductList() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(R.drawable.baseline_account_circle_24, "Shirt", "description 7",29.99, 15, 7));
        productList.add(new Product(R.drawable.baseline_account_circle_24, "Jeans", "description 8",49.99, 10, 8));
        productList.add(new Product(R.drawable.baseline_account_circle_24, "Jacket", "description 9",99.99, 5, 9));
        return productList;
    }
}
