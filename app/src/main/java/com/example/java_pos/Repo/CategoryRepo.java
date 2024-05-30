package com.example.java_pos.Repo;

import com.example.java_pos.Models.Category;
import com.example.java_pos.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CategoryRepo implements ICategoryRepo{

    List<Category> categories;

    public CategoryRepo() {

        categories = new ArrayList<>();

        categories.add(new Category("category 1", 1, R.drawable.baseline_account_circle_24));
        categories.add(new Category("category 2", 2, R.drawable.baseline_account_circle_24));
        categories.add(new Category("category 3", 3, R.drawable.baseline_account_circle_24));
        categories.add(new Category("category 4", 4, R.drawable.baseline_account_circle_24));


    }

    @Override
    public List<Category> getAllCategories() {
        return Collections.unmodifiableList(categories);
    }
}
