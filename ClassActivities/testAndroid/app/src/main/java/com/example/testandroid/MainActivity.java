package com.example.testandroid;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View; // Missing import for View
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Declare Spinner and TextView globally
    Spinner sp;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the views inside onCreate
        sp = findViewById(R.id.spnID);
        tv = findViewById(R.id.txtID);
    }

    // Button handler method
    public void btnHandler(View view) {
        // Get the selected item from the spinner
        String selected_university = String.valueOf(sp.getSelectedItem());

        // Check the selected university and set the TextView accordingly
        if (selected_university.equals("2240099")) {
            tv.setText("AHmad:2240099");
        } else if (selected_university.equals("2240096")) {
            tv.setText("Albara:2240096");
        } else {
            tv.setText("Mohammed Abdo:2214488");
        }
    }
}
