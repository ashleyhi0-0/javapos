package com.example.java_pos.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.java_pos.R;
import com.example.java_pos.Repo.AccountRepo;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;
    private Button registerButton;
    private AccountRepo accountRepo;

   TextView haveAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_actvity);

        accountRepo = new AccountRepo(this);

        usernameEditText = findViewById(R.id.username_register);
        passwordEditText = findViewById(R.id.password_confirm);
        registerButton = findViewById(R.id.register_btn);
        haveAccount = findViewById(R.id.loginBtn);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if(username.isEmpty() && password.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please enter username and password.", Toast.LENGTH_SHORT).show();
                }

                else if (accountRepo.register(username, password)) {
                    Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegisterActivity.this, "Username already exists", Toast.LENGTH_SHORT).show();
                }
            }
        });
        haveAccount.setOnClickListener(v -> {

            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

        });
    }
}
