package com.example.java_pos.Views;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.java_pos.R;
import com.example.java_pos.Repo.Adapter.CategoryAdapter;
import com.example.java_pos.Repo.CategoryRepo;

public class MainActivity extends AppCompatActivity {

    CategoryRepo categoryRepo;
    CategoryAdapter categoryAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.category_activity);
    }
}
