package com.example.umbrellaweatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText etZip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etZip = findViewById(R.id.etZip);
    }

    public void onClick(View view) {

        int zip = Integer.parseInt(etZip.getText().toString());
        Intent toCurrentWeatherIntent = new Intent(this, CurrentWeather.class);
        toCurrentWeatherIntent.putExtra("zipcode", zip);
        startActivity(toCurrentWeatherIntent);
    }
}
