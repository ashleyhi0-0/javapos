package com.example.java_pos.Repo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.java_pos.Models.Category;
import com.example.java_pos.R;
import com.example.java_pos.Views.ProductActivity;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    List<Category> categories;

    Context context;

    public CategoryAdapter(List<Category> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_cardview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {

        holder.textView.setText(categories.get(position).getName());
        holder.imageView.setImageResource(categories.get(position).getImage());

        int categoryId = categories.get(position).getId();

        holder.cardView.setOnClickListener(v -> {

            Log.d("", "categoryId: "+categoryId);

            Intent intent = new Intent(context, ProductActivity.class);
            intent.putExtra("categoryId", categoryId);
            context.startActivity(intent);

        });

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView textView;
        ImageView imageView;
        public ViewHolder(@NonNull View item) {
            super(item);

            cardView = item.findViewById(R.id.category_card);
            textView = item.findViewById(R.id.category_title);
            imageView = item.findViewById(R.id.category_image);
        }
    }
}
