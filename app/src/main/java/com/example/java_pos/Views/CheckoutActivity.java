package com.example.java_pos.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.widget.TextView;

import com.example.java_pos.Helper.SharedPrefHelper;
import com.example.java_pos.Models.Order;
import com.example.java_pos.Models.Product;
import com.example.java_pos.R;
import com.example.java_pos.Repo.Adapter.OrderAdapter;

import androidx.annotation.Nullable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CheckoutActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    OrderAdapter orderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity);

        recyclerView = findViewById(R.id.order_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Retrieve the list of orders from SharedPreferences
        List<Order> orderList = retrieveOrderList();

        if (orderList != null && !orderList.isEmpty()) {
            // Display the list of orders using the OrderAdapter
            orderAdapter = new OrderAdapter(orderList);
            recyclerView.setAdapter(orderAdapter);
        } else {
            // Handle case when order list is empty or not found
            Toast.makeText(this, "No order details found", Toast.LENGTH_SHORT).show();
            finish(); // Close the activity if no order details are found
        }
    }

    // Method to retrieve the list of orders from SharedPreferences
    private List<Order> retrieveOrderList() {
        // Retrieve the list of orders from SharedPreferences
        List<Order> orderList = SharedPrefHelper.retrieveOrderList(this);

        // If the retrieved list is null, initialize it as an empty list
        if (orderList == null) {
            orderList = new ArrayList<>();
        }

        return orderList;
    }

}