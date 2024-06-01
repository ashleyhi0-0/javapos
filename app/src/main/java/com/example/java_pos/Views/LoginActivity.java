package com.example.java_pos.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.java_pos.Models.Account;
import com.example.java_pos.R;
import com.example.java_pos.Repo.AccountRepo;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;
    private Button loginButton;

    private TextView registerButton;
    private AccountRepo accountRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        accountRepo = new AccountRepo(this);

        usernameEditText = findViewById(R.id.username_input);
        passwordEditText = findViewById(R.id.password_input);
        loginButton = findViewById(R.id.login);
        registerButton = findViewById(R.id.register);

        // Check if a user is already logged in
        Account loggedInAccount = accountRepo.getLoggedInAccount();
        if (loggedInAccount != null && loggedInAccount.isLoggedIn()) {
            // Proceed to the main activity
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                Account account = accountRepo.login(username, password);
                if (account != null) {
                    Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                    // Proceed to the main activity
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
