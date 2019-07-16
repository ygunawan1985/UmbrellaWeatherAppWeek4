package com.example.umbrellaweatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.umbrellaweatherapp.model.forecastresponse.Forecast;

import java.util.List;

public class ForecastActivity extends AppCompatActivity {

    RecyclerView rvForecast;
    List<Forecast> forecastList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        rvForecast = findViewById(R.id.rvForecast);
        rvForecast.setLayoutManager(new LinearLayoutManager(this));

        Intent receivedIntent = getIntent();
        Bundle receivedBundle = receivedIntent.getExtras();
        forecastList = receivedBundle.getParcelableArrayList("forecastList");

        ForecastListAdapter forecastListAdapter = new ForecastListAdapter(forecastList);
        rvForecast.setAdapter(forecastListAdapter);
    }
}
