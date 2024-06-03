package com.example.java_pos.Views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.java_pos.Helper.SharedPrefHelper;
import com.example.java_pos.Models.Account;
import com.example.java_pos.R;
import com.example.java_pos.Repo.AccountRepo;

import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private Button logoutButton;

    private TextView userText;

    private AccountRepo accountRepo;

    private Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        userText = findViewById(R.id.profile_title);

        // Initialize the AccountRepo
        accountRepo = new AccountRepo(this);
        // Retrieve the logged-in account
        account = accountRepo.getLoggedInAccount();

        userText.setText("Hi, "+ account.getUsername());

        // Initialize the logout button
        logoutButton = findViewById(R.id.logout_button);

        // Set up the logout button click listener
        logoutButton.setOnClickListener(v -> logoutUser());
    }

    // Method to log out the user
    private void logoutUser() {
        accountRepo.logout(account.getUsername());

        // Display a toast message
        Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show();

        // Navigate back to the login screen
        Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, R.anim.slide_out_right); // Custom animations for back navigation
    }
}
