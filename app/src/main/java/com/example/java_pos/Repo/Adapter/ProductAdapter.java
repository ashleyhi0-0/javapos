package com.example.java_pos.Repo.Adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.java_pos.Models.Product;
import com.example.java_pos.R;
import com.example.java_pos.Views.CartActivity;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    List<Product> products;

    private Context context;

    public ProductAdapter(List<Product> products, Context context) {
        this.products = products;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {

        Product product = products.get(position);

        holder.productName.setText(product.getName());
        holder.productPrice.setText(String.format("Price: $%.2f", product.getPrice()));
        holder.productQuantity.setText("Quantity: "+product.getQuantity());
        holder.productImage.setImageResource(product.getImageId());

        int productId = product.getProductId();

        holder.productImage.setOnClickListener(v -> {

            Intent intent = new Intent(context, CartActivity.class);
            intent.putExtra("productId", productId);
            context.startActivity(intent);

        });

    }

    public void updateProductList(List<Product> newProductList) {
        products = newProductList;

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView productName, productPrice, productQuantity;
        ImageView productImage;

        public ViewHolder(@NonNull View item) {
            super(item);

            productName = item.findViewById(R.id.product_name);
            productPrice = item.findViewById(R.id.product_price);
            productQuantity = item.findViewById(R.id.product_quantity);
            productImage = item.findViewById(R.id.product_image);
        }
    }
}