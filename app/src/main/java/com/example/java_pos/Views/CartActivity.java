package com.example.java_pos.Views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.java_pos.Helper.SharedPrefHelper;
import com.example.java_pos.Models.Product;
import com.example.java_pos.Models.Order;
import com.example.java_pos.R;
import com.google.android.material.slider.Slider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private ImageView productImage;
    private TextView productBrand;
    private TextView productType;

    private Slider amountSlider;

    private Button addToCartButton;
    private TextView productDescription;
    private TextView productPrice;
    private TextView amountValue;

    private Product currentProduct;

    private List<Order> orderList;

    int productId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addtocart_cardview);

        initializeUI();

        // Retrieve the product ID from the intent
        productId = getIntent().getIntExtra("productId", -1);

        loadProductDetails(productId);

        // Set up the slider listener
        setupSliderListener();
    }

    private void initializeUI() {
        productImage = findViewById(R.id.product_image);
        productBrand = findViewById(R.id.product_brand);
        productType = findViewById(R.id.product_type);
        productDescription = findViewById(R.id.description);
        productPrice = findViewById(R.id.product_price);
        amountValue = findViewById(R.id.amount_value);
        amountSlider = findViewById(R.id.amount_slider);
        addToCartButton = findViewById(R.id.add2cart_btn);
        orderList = new ArrayList<>();
    }

    private void loadProductDetails(int productId) {
        // Load the product details from your data source
        // For demonstration purposes, we'll use hardcoded data

        // Example product list
        List<Product> allProducts = getAllProducts();

        for (Product product : allProducts) {
            if (product.getProductId() == productId) {
                currentProduct = product;
                productImage.setImageResource(product.getImageId());
                productBrand.setText(product.getName());
                productType.setText(product.getType());
                productDescription.setText(product.getDesc());
                productPrice.setText(String.format("$%.2f", product.getPrice()));
                amountValue.setText(String.format("Quantity: %d", product.getQuantity()));
                amountSlider.setValueFrom(1);
                amountSlider.setValueTo(product.getQuantity());
                amountSlider.setValue(1);  // Initialize the slider with the minimum value
                break;
            }
        }
    }

    private List<Product> getAllProducts() {
        // This method should return all products available
        // Here, we'll use the same hardcoded lists as in the MainActivity for demonstration purposes

        List<Product> products = new ArrayList<>();
        products.addAll(MainActivity.generateGroceriesList());
        products.addAll(MainActivity.generateClothingList());
        products.addAll(MainActivity.generateElectronicsList());

        return products;
    }

    private void setupSliderListener() {
        amountSlider.addOnChangeListener((slider, value, fromUser) -> {
            int quantity = (int) value;
            amountValue.setText(String.format("Quantity: %d", quantity));
        });

        addToCartButton.setOnClickListener(view -> {
            int selectedQuantity = (int) amountSlider.getValue();
            if (currentProduct != null) {
                // Retrieve existing order list from SharedPreferences
                List<Order> orderList = SharedPrefHelper.retrieveOrderList(this);

                // Initialize an empty list if the retrieved list is null
                if (orderList == null) {
                    orderList = new ArrayList<>();
                }

                // Add the new order to the list
                orderList.add(new Order(currentProduct.getName(), selectedQuantity, currentProduct.getPrice()));

                // Store the updated order list in SharedPreferences
                SharedPrefHelper.storeOrderList(this, orderList);

                // Navigate to CheckoutActivity
                Intent intent = new Intent(CartActivity.this, CheckoutActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
