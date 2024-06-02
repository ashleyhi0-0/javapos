package com.example.java_pos.Repo.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.java_pos.Models.Product;
import com.example.java_pos.R;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    List<Product> products;
    Context context;

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

        holder.productName.setText(products.get(position).getName());
        holder.productPrice.setText(String.valueOf(products.get(position).getPrice()));
        holder.productQuantity.setText(String.valueOf(products.get(position).getQuantity()));
        holder.productImage.setImageResource(products.get(position).getImageId());
        int productId = products.get(position).getProductId();

       holder.productImage.setOnClickListener(v -> {
           Log.d("", "This prodcut"+productId);
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView productName,  productPrice, productQuantity;
        ImageView productImage;

        public ViewHolder(@NonNull View item) {
            super(item);

            productImage = item.findViewById(R.id.product_image);
            productName = item.findViewById(R.id.product_name);
            productPrice = item.findViewById(R.id.product_price);
            productQuantity = item.findViewById(R.id.product_quantity);
        }
    }
}
