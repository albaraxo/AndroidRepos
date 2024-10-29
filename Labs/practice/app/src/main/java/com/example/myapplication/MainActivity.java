package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void selectOutOfGPA(View view){
        RadioGroup radioGroup = findViewById(R.id.RG);
        int Id = radioGroup.getCheckedRadioButtonId();

        if (Id == R.id.four) {
            Toast.makeText(this, "Four", Toast.LENGTH_SHORT).show();
        } else if (Id == R.id.five) {
            Toast.makeText(this, "Five", Toast.LENGTH_SHORT).show();
        } else if (Id == R.id.noGPA) {
            Toast.makeText(this, "no GPA", Toast.LENGTH_SHORT).show();
        }

    }
    public void onCheackBoxClic(View view){
        CheckBox cb1 = findViewById(R.id.tomatto);
        CheckBox cb2 = findViewById(R.id.carrot);
        CheckBox cb3 = findViewById(R.id.potatto);

        if (cb1.isChecked()){
            Toast.makeText(this, "Tomatto", Toast.LENGTH_SHORT).show();
        }
        if (cb2.isChecked()){
            Toast.makeText(this, "carrot", Toast.LENGTH_SHORT).show();
        }
        if (cb3.isChecked()){
            Toast.makeText(this, "potatto", Toast.LENGTH_SHORT).show();
        }

        if(!(cb1.isChecked())){
            Toast.makeText(this, "no Tomatto", Toast.LENGTH_SHORT).show();
        }
    }

    public void toggleClicked(View view){
        ToggleButton tb = findViewById(R.id.toggle);
        boolean mode = tb.isChecked();
        if (mode){
            Toast.makeText(this, "ON MONDEEE", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "OFFF modode", Toast.LENGTH_SHORT).show();
        }
    }
}

