package com.example.java_pos.Views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.java_pos.Models.Account;
import com.example.java_pos.R;
import com.example.java_pos.Repo.AccountRepo;

public class LoginActivity extends AppCompatActivity {

    private EditText passwordInput, usernameInput;

    private TextView createAccount;

    private Button loginBtn;

    private AccountRepo accountRepo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        // initializing ui

        accountRepo = new AccountRepo(this);

        passwordInput = findViewById(R.id.password_input);
        usernameInput = findViewById(R.id.username_input);
        createAccount = findViewById(R.id.register);
        loginBtn = findViewById(R.id.login);

        // check if logged in already
        Account loggedIn = accountRepo.getLoggedInAccount();
        if(loggedIn != null && loggedIn.isLoggedIn()) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
        loginBtn.setOnClickListener(v -> {
            String username = usernameInput.getText().toString();
            String password = passwordInput.getText().toString();

            Account account = accountRepo.login(username, password);

            if(account != null ) {
                Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }else {
                Toast.makeText(this, "Invalid username and passowrd", Toast.LENGTH_SHORT).show();
            }
        });
        createAccount.setOnClickListener(v ->  {

            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            finish();
        });
    }
}