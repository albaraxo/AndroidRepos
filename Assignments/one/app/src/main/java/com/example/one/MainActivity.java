package com.example.one;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.one.R;

public class MainActivity extends AppCompatActivity {

    private Spinner spn;
    private Button button;
    private ImageView myImageView;
    private TextView url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spn = findViewById(R.id.spn);
        button = findViewById(R.id.myButton);
        myImageView = findViewById(R.id.myImageView);
        url = findViewById(R.id.urlID);

        // Set up the Spinner with options
        String[] options = {"University of Jeddah", "King Abdulaziz University", "Taibah University"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn.setAdapter(adapter);

        // Set up the button click listener
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = spn.getSelectedItemPosition();

                switch (position) {
                    case 0:
                        myImageView.setImageResource(R.drawable.jedddah_logo);
                        setUrl("https://www.uj.edu.sa/en");  // Set the URL and make it clickable
                        break;
                    case 1:
                        myImageView.setImageResource(R.drawable.kau_logo);
                        setUrl("https://www.kau.edu.sa/home_english.aspx");  // Set the URL and make it clickable
                        break;
                    case 2:
                        myImageView.setImageResource(R.drawable.taibah_logo);
                        setUrl("https://www.taibahu.edu.sa/Pages/EN/Home.aspx");  // Set the URL and make it clickable
                        break;
                    default:
                        myImageView.setImageResource(0);
                        url.setText("");  // Clear the URL if no selection
                        break;
                }
            }
        });
    }

    // Method to set URL and make it clickable
    private void setUrl(final String websiteUrl) {
        url.setText(websiteUrl);  // Display the URL in the TextView
        url.setOnClickListener(new View.OnClickListener() {  // Make TextView clickable
            @Override
            public void onClick(View v) {
                // Open the URL in a browser
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(websiteUrl));
                startActivity(browserIntent);
            }
        });
    }
}
