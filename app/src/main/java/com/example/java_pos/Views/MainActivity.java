package com.example.java_pos.Views;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.java_pos.Models.Category;
import com.example.java_pos.R;
import com.example.java_pos.Repo.Adapter.CategoryAdapter;
import com.example.java_pos.Repo.CategoryRepo;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    CategoryRepo categoryRepo;
    CategoryAdapter categoryAdapter;
    RecyclerView recyclerView;

    List<Category> categories;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.category_activity);

        categoryRepo = new CategoryRepo();

        categoryList();


    }

    private void categoryList() {

        recyclerView = findViewById(R.id.category_recview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        fetchAndDisplayCategory();
    }

    private void fetchAndDisplayCategory() {

        categories = categoryRepo.getAllCategories();

        categoryAdapter = new CategoryAdapter(categories, this);

        recyclerView.setAdapter(categoryAdapter);

    }
}
