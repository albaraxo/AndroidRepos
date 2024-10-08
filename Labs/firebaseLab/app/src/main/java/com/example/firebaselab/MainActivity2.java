package com.example.firebaselab;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity2 extends AppCompatActivity {

    private DatabaseReference databaseStudents;
    private EditText editTextID;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Initialize Firebase Database reference
        databaseStudents = FirebaseDatabase.getInstance().getReference("students");

        // Initialize EditText and TextView from the layout
        editTextID = findViewById(R.id.editTextID);
        textView = findViewById(R.id.tv);
    }

    public void findName(View view) {
        String studentID = editTextID.getText().toString().trim();

        if (!studentID.isEmpty()) {
            // Retrieve the student name from Firebase by student ID
            databaseStudents.child(studentID).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        // ID exists, get the student's name
                        String studentName = dataSnapshot.getValue(String.class);
                        textView.setText("Student Name: " + studentName); // Display the name
                    } else {
                        // ID doesn't exist, show a message
                        textView.setText("No student found with ID: " + studentID);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }

                // Removed the onCancelled() method
            });
        } else {
            // If the field is empty, show a warning
            Toast.makeText(this, "Please enter a student ID", Toast.LENGTH_SHORT).show();
        }
    }
    public void goBack(View view){
        Intent intent = new Intent(MainActivity2.this, MainActivity.class);
        startActivity(intent);  // Start the new activity
    }
}