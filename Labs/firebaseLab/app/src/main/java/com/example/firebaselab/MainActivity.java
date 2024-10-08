package com.example.firebaselab;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private EditText editTextID, editTextName;
    private DatabaseReference databaseStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseStudents = FirebaseDatabase.getInstance().getReference("students");

        // Initialize the EditText fields
        editTextID = findViewById(R.id.editTextID);
        editTextName = findViewById(R.id.editTextName);
    }

    public void saveStudentInfo(android.view.View view) {
        String studentID = editTextID.getText().toString().trim();
        String studentName = editTextName.getText().toString().trim();

        // Check if the fields are not empty
        if (!studentID.isEmpty() && !studentName.isEmpty()) {
            // Save student info in Firebase with student ID as the key and name as the value
            databaseStudents.child(studentID).setValue(studentName);

            // Show confirmation toast
            Toast.makeText(MainActivity.this, "Student info saved", Toast.LENGTH_SHORT).show();

            // Clear input fields after saving
            editTextID.setText("");
            editTextName.setText("");
        } else {
            // Show a warning if fields are empty
            Toast.makeText(MainActivity.this, "Please enter both ID and Name", Toast.LENGTH_SHORT).show();
        }
    }

    public void goNextPage(View view) {
        // Create an Intent to start the NextActivity
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        startActivity(intent);  // Start the new activity
    }
}
