package com.example.java_pos.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.java_pos.Helper.SharedPrefHelper;
import com.example.java_pos.Models.Order;
import com.example.java_pos.R;
import com.example.java_pos.Repo.Adapter.OrderAdapter;

import java.util.ArrayList;
import java.util.List;

public class CheckoutActivity extends AppCompatActivity implements OrderAdapter.OnOrderDeleteListener {

    // UI components
    private RecyclerView recyclerView;
    private OrderAdapter orderAdapter;
    private ImageView payment, address;
    private TextView pay_selected, instant, cod;
    private Dialog paymentDialog;
    private TextView subtotalText, charges, grandTotalText;
    private List<Order> orderList;

    private Button payBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity);

        // Initialize UI components
        initializeViews();

        // Setup RecyclerView with LayoutManager and Adapter
        setupRecyclerView();

        // Setup payment method selection dialog
        setupPaymentDialog();

        // Retrieve orders from SharedPreferences and bind to Adapter
        orderList = retrieveOrderList();
        orderAdapter = new OrderAdapter(orderList, this);
        recyclerView.setAdapter(orderAdapter);

        // Calculate and display the order totals
        calculateTotals(orderList);

        payBtn.setOnClickListener(v -> {

            if(!orderList.isEmpty()) {
                Toast.makeText(this, "Order successful!", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Order is empty, please add something to your cart...", Toast.LENGTH_SHORT).show();
            }

        });
    }

    // Initialize all the views from the layout
    private void initializeViews() {
        payment = findViewById(R.id.payment_select);
        address = findViewById(R.id.address_slct);
        recyclerView = findViewById(R.id.order_rv);
        subtotalText = findViewById(R.id.subtotal_amt);
        charges = findViewById(R.id.charges_amt);
        grandTotalText = findViewById(R.id.grand_amt);
        pay_selected = findViewById(R.id.pay_selected);
        payBtn = findViewById(R.id.pay_btn);
    }

    // Setup RecyclerView with a LinearLayoutManager
    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    // Setup the payment method selection dialog
    private void setupPaymentDialog() {
        paymentDialog = new Dialog(this);
        paymentDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        paymentDialog.setContentView(R.layout.custom_dialogbox);
        instant = paymentDialog.findViewById(R.id.instant);
        cod = paymentDialog.findViewById(R.id.cod);
        paymentDialog.setCancelable(false);

        // Show the payment dialog when the payment ImageView is clicked
        payment.setOnClickListener(v -> paymentDialog.show());

        // Set the payment method to "Cash" when COD is selected
        cod.setOnClickListener(v -> selectPaymentMethod("Cash"));

        // Set the payment method to "Instant" when Instant Payment is selected
        instant.setOnClickListener(v -> selectPaymentMethod("Instant"));
    }

    // Method to handle payment method selection
    private void selectPaymentMethod(String method) {
        pay_selected.setText(method);
        paymentDialog.dismiss();
    }

    // Retrieve the order list from SharedPreferences
    private List<Order> retrieveOrderList() {
        List<Order> orderList = SharedPrefHelper.retrieveOrderList(this);
        return orderList != null ? orderList : new ArrayList<>();
    }

    // Calculate and display the subtotal, delivery charges, and grand total
    private void calculateTotals(List<Order> orderList) {
        double subtotal = 0.0;
        double deliveryCharges = 2.0; // Assuming a fixed delivery charge

        // Calculate the subtotal from the order list
        for (Order order : orderList) {
            subtotal += order.getPrice() * order.getQuantity();
        }

        double grandTotal = subtotal + deliveryCharges;
        updateTotalViews(subtotal, deliveryCharges, grandTotal);
    }

    // Update the TextViews with calculated totals
    private void updateTotalViews(double subtotal, double deliveryCharges, double grandTotal) {
        subtotalText.setText(String.format("$%.2f", subtotal));
        charges.setText(String.format("$%.2f", deliveryCharges));
        grandTotalText.setText(String.format("$%.2f", grandTotal));
    }

    // Callback method when an order is deleted from the list
    @Override
    public void onOrderDelete(int position) {
        // Remove the order from the list
        orderList.remove(position);

        // Notify the adapter about the removed item
        orderAdapter.notifyItemRemoved(position);
        orderAdapter.notifyItemRangeChanged(position, orderList.size());

        // Recalculate and update the totals
        calculateTotals(orderList);

        // Save the updated order list to SharedPreferences
        SharedPrefHelper.storeOrderList(this, orderList);
    }
}
