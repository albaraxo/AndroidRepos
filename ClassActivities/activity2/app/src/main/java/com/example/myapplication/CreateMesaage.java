package com.example.myapplication;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CreateMesaage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void onSendMessage(View view) {
        // get the from textField element, then store the value in variable.
        EditText messageView = findViewById(R.id.id);
        String textID = messageView.getText().toString();
        EditText messageView2 = findViewById(R.id.studentName);
        String textName = messageView2.getText().toString();
        Spinner mySpinner = (Spinner) findViewById(R.id.spn);
        String spnText = mySpinner.getSelectedItem().toString();

        // create new Intent, send message to the that class, then start it.
        Intent intent = new Intent(this, RecieveMessage.class);
        intent.putExtra(RecieveMessage.GetID, textID);
        intent.putExtra(RecieveMessage.GetName, textName);
        intent.putExtra(RecieveMessage.GetSpen,spnText);
        startActivity(intent);
    }

}