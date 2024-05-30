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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_model, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {

        holder.productName.setText(products.get(position).getProductName());
        holder.productDesc.setText(products.get(position).getProductDesc());
        holder.productPrice.setText(String.valueOf(products.get(position).getPrice()));
        holder.productQuantity.setText(String.valueOf(products.get(position).getQuantity()));

        int productId = products.get(position).getProductId();

        holder.productCard.setOnClickListener(v -> {

            Log.d("", "productId: "+productId);

        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView productName, productDesc, productPrice, productQuantity;
        ImageView productImage;
        CardView productCard;
        public ViewHolder(@NonNull View item) {
            super(item);

            productCard = item.findViewById(R.id.product_card);
            productImage = item.findViewById(R.id.product_image);
            productName = item.findViewById(R.id.product_name);
            productDesc = item.findViewById(R.id.product_desc);
            productPrice = item.findViewById(R.id.product_prc);
            productQuantity = item.findViewById(R.id.product_qnt);
        }
    }
}
