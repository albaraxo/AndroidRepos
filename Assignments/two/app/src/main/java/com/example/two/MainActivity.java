package com.example.two;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private EditText locationInput;
    private Button checkWeatherButton;
    private TextView weatherInfo;
    private ImageView weatherIcon;  // Reference to the ImageView for weather icons
    private RadioGroup tempUnitGroup;
    private RadioButton radioCelsius;
    private RadioButton radioFahrenheit;

    // Replace with your actual OpenWeatherMap API key
    private final String API_KEY = "this is a ture apis key:384834834hdufhsd0fw80ehduew9";  // Replace with your API key

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationInput = findViewById(R.id.locationInput);
        checkWeatherButton = findViewById(R.id.checkWeatherButton);
        weatherInfo = findViewById(R.id.weatherInfo);
        weatherIcon = findViewById(R.id.weatherIcon);  // Initialize the ImageView
        tempUnitGroup = findViewById(R.id.tempUnitGroup);
        radioCelsius = findViewById(R.id.radioCelsius);
        radioFahrenheit = findViewById(R.id.radioFahrenheit);

        checkWeatherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location = locationInput.getText().toString();
                if (!location.isEmpty()) {
                    // Show a message and start fetching weather
                    Toast.makeText(MainActivity.this, "Fetching weather for " + location, Toast.LENGTH_SHORT).show();

                    // Determine whether Celsius or Fahrenheit is selected
                    String units;
                    if (radioCelsius.isChecked()) {
                        units = "metric";  // Celsius (metric units)
                    } else {
                        units = "imperial";  // Fahrenheit (imperial units)
                    }

                    new FetchWeatherTask().execute(location, units);
                } else {
                    // Show a message if the input is empty
                    Toast.makeText(MainActivity.this, "Please enter a location", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // AsyncTask to fetch weather data from OpenWeatherMap API
    private class FetchWeatherTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String location = params[0];
            String units = params[1];  // Unit passed (metric or imperial)

            String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + location + "&appid=" + API_KEY + "&units=" + units;
            try {
                // Create a URL object with the constructed API URL
                URL url = new URL(apiUrl);

                // Open a connection to the API server
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                // Specify the request method as GET (since we're retrieving data)
                connection.setRequestMethod("GET");

                // Send the request to the server and establish the connection
                connection.connect();

                // Read the response from the API
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                // Append each line of the response to the StringBuilder
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                // Close the reader after reading the response
                reader.close();

                // Return the response as a string
                return response.toString();

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                try {
                    // Parse the JSON response
                    JSONObject jsonObject = new JSONObject(result);
                    if (jsonObject.has("main")) {
                        JSONObject main = jsonObject.getJSONObject("main");
                        JSONObject wind = jsonObject.getJSONObject("wind");

                        double temp = main.getDouble("temp");
                        int humidity = main.getInt("humidity");
                        double windSpeed = wind.getDouble("speed");
                        String weather = jsonObject.getJSONArray("weather").getJSONObject(0).getString("main");

                        // Display the temperature unit based on the selected radio button
                        String unitSymbol = radioCelsius.isChecked() ? "°C" : "°F";

                        // Display the weather information
                        weatherInfo.setText("Temperature: " + temp + unitSymbol + "\n" +
                                "Humidity: " + humidity + "%\n" +
                                "Wind Speed: " + windSpeed + " m/s\n" +
                                "Condition: " + weather);
                        weatherInfo.setVisibility(View.VISIBLE);

                        // Show the corresponding weather icon based on the condition
                        weatherIcon.setVisibility(View.VISIBLE);
                        switch (weather.toLowerCase()) {
                            case "clear":
                            case "sunny":
                                weatherIcon.setImageResource(R.drawable.sun); // Icon for sunny weather
                                break;
                            case "clouds":
                                weatherIcon.setImageResource(R.drawable.cloud); // Icon for cloudy weather
                                break;
                            case "rain":
                                weatherIcon.setImageResource(R.drawable.rain); // Icon for rainy weather
                                break;
                            case "snow":
                                weatherIcon.setImageResource(R.drawable.snow); // Icon for snowy weather
                                break;
                            case "fog":
                            case "mist":
                                weatherIcon.setImageResource(R.drawable.fog); // Icon for foggy weather
                                break;
                            default:
                                break;
                        }

                    } else {
                        weatherInfo.setText("Invalid location or data not found.");
                        weatherIcon.setVisibility(View.GONE); // Hide icon if data is invalid
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    weatherInfo.setText("Failed to parse weather data.");
                    weatherIcon.setVisibility(View.GONE); // Hide icon on error
                }
            } else {
                weatherInfo.setText("Failed to retrieve weather data. Check your network connection.");
                weatherIcon.setVisibility(View.GONE); // Hide icon if no data
            }
        }
    }
}
