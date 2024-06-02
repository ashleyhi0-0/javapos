package com.example.java_pos.Repo.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.java_pos.Helper.SharedPrefHelper;
import com.example.java_pos.Models.Order;
import com.example.java_pos.R;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private List<Order> orderList;

    private static OnOrderDeleteListener deleteListener;

    // Constructor to initialize with a list of orders
    public OrderAdapter(List<Order> orderList, OnOrderDeleteListener deleteListener) {
        this.orderList = orderList;
        this.deleteListener = deleteListener;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_cardview, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        // Bind data to your ViewHolder
        Order order = orderList.get(position);
        holder.bind(order);
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    // ViewHolder class
    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        private TextView productNameTextView;
        private TextView quantityTextView;
        private TextView priceTextView;

        private ImageView delete;


        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize views from itemView
            productNameTextView = itemView.findViewById(R.id.order_name);
            quantityTextView = itemView.findViewById(R.id.amount_qty);
            priceTextView = itemView.findViewById(R.id.order_price);
            delete = itemView.findViewById(R.id.deleteIcon);
        }

        public void bind(Order order) {
            // Bind data to views
            productNameTextView.setText(order.getProductName());
            quantityTextView.setText(String.valueOf(order.getQuantity()));
            priceTextView.setText("$" + order.getPrice());

            delete.setOnClickListener(v -> deleteListener.onOrderDelete(getAdapterPosition()));
        }
    }
    public interface OnOrderDeleteListener {
        void onOrderDelete(int position);
    }
}
