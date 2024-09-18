package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.widget.TextView;

public class RecieveMessage extends AppCompatActivity {

    public static final String GetID = "key1";
    public static final String GetName = "key2";
    public static final String GetSpen = "key3";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ///
        Intent intent = getIntent();

        String messageText = "The ID: " + intent.getStringExtra(GetID);
        TextView messageView = findViewById(R.id.message1);
        messageView.setText(messageText);

        String messageStudent = "the Name: "+ intent.getStringExtra(GetName);
        TextView messageView2 = findViewById(R.id.message2);
        messageView2.setText(messageStudent);

        String messgageSpn = "the Major: " + intent.getStringExtra(GetSpen);
        TextView messageView3 = findViewById(R.id.message3);
        messageView3.setText(messgageSpn);
    }
}