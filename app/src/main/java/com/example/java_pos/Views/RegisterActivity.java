package com.example.java_pos.Views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.java_pos.Models.Account;
import com.example.java_pos.R;
import com.example.java_pos.Repo.AccountRepo;

public class RegisterActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;

    private AccountRepo accountRepo;

    private Account account;

    private Button registerBtn;

    private TextView haveAccount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_actvity);
        // initialize UI
        accountRepo = new AccountRepo(this);
        usernameEditText = findViewById(R.id.username_register);
        passwordEditText = findViewById(R.id.password_confirm);
        haveAccount = findViewById(R.id.loginBtn);
        registerBtn = findViewById(R.id.register_btn);

        registerBtn.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            if(username.isEmpty() || password.isEmpty()) {
                Toast.makeText(RegisterActivity.this, "Input a username and password.", Toast.LENGTH_SHORT).show();
            }else if(accountRepo.register(username, password)) {
                Toast.makeText(RegisterActivity.this, "Registered successfully.", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(RegisterActivity.this, "Username already exist.", Toast.LENGTH_SHORT).show();
            }


        });

        haveAccount.setOnClickListener(v -> {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        });


    }
}