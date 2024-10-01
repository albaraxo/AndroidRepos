package com.example.registrationpage;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<String> validUsernames = new ArrayList<>();
    public static String password = "admin";
    private EditText usernameEditText;
    private EditText passwordEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);

        validUsernames.add("admin");
        validUsernames.add("user1");
        validUsernames.add("user2");
    }
    public void doLogin(View view) {
        // Get the entered username and password
        String valueOfUsername = usernameEditText.getText().toString();
        String valueOfPassword = passwordEditText.getText().toString();

        // Simple validation for demo purposes
        if (validUsernames.contains(valueOfUsername) && valueOfPassword.equals(password)) {
            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
        }
    }
    public void openForgotPasswordPage(View view) {
        Intent intent = new Intent(MainActivity.this, ForgetPasswordActivity.class);
        startActivity(intent);  // This starts the ForgotPasswordActivity
    }
}