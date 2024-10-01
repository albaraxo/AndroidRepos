package com.example.registrationpage;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ForgetPasswordActivity extends AppCompatActivity {
    private EditText userEditText;
    private EditText newPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forget_password);

        userEditText = findViewById(R.id.username);
        newPasswordEditText = findViewById(R.id.password);
    }

    public void resetPassword(View view) {
        String enteredUsername = userEditText.getText().toString();
        String newPassword = newPasswordEditText.getText().toString();

        if (MainActivity.validUsernames.contains(enteredUsername)) {
            if (!newPassword.isEmpty()) {
                MainActivity.password = newPassword;  // Update the password in MainActivity
                Toast.makeText(this, "Password reset successful", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Please enter a new password", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Username not found", Toast.LENGTH_SHORT).show();
        }
    }
}