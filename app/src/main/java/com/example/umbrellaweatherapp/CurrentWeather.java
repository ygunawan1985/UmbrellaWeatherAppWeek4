package com.example.umbrellaweatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.umbrellaweatherapp.model.datasource.remote.rxjava.Callback;
import com.example.umbrellaweatherapp.model.datasource.remote.rxjava.WeatherRepo;
import com.example.umbrellaweatherapp.model.weatherresponse.WeatherResponse;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.umbrellaweatherapp.model.datasource.remote.retrofit.UrlConstants.ICON_URL;

public class CurrentWeather extends AppCompatActivity implements Callback {

    private ImageView ivWeatherIcon;
    private TextView tvCity;
    private TextView tvWeatherDescription;
    private TextView tvCurrentTemp;
    private TextView tvHighLowTemps;
    private TextView tvDateTime;

    public static final String APPID = "f0d3041024e4f2d29366a34bc8b74f69";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_weather);

        ivWeatherIcon = findViewById(R.id.ivWeatherIcon);
        tvCity = findViewById(R.id.tvCity);
        tvWeatherDescription = findViewById(R.id.tvWeatherDescription);
        tvCurrentTemp = findViewById(R.id.tvCurrentTemp);
        tvHighLowTemps = findViewById(R.id.tvHighLowTemps);
        tvDateTime = findViewById(R.id.tvDateTime);

        Intent receivedIntent = getIntent();
        int zip = receivedIntent.getIntExtra("zipcode", 0);

        WeatherRepo.getWeather(this, zip, APPID);
    }

    @Override
    public void getWeatherResponse(WeatherResponse weatherResponse) {

        //Retrieve weather icon then do Glide
        String icon = weatherResponse.getWeather().get(0).getIcon();
        String weatherIconUrl = ICON_URL + icon + "@2x.png";
        Float currentTempInKelvin = weatherResponse.getMain().getTemp();
        int currentTemp = convertToFahrenheit(currentTempInKelvin);
        int highTemp = convertToFahrenheit(weatherResponse.getMain().getTempMax());
        int lowTemp = convertToFahrenheit(weatherResponse.getMain().getTempMin());
        String highandLowTemps = highTemp + "º/" + lowTemp + "º";

        SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z");
        String strDate = formatter.format(new Date());

        Glide.with(this).load(weatherIconUrl).into(ivWeatherIcon);
        tvCity.setText(weatherResponse.getName());
        tvWeatherDescription.setText(weatherResponse.getWeather().get(0).getDescription());
        tvCurrentTemp.setText(String.valueOf(currentTemp) + "º");
        tvDateTime.setText(strDate);
        tvHighLowTemps.setText(highandLowTemps);
    }

    public int convertToFahrenheit(float temp) {
        float tempInK = (float) (((temp - 273) * (9.0 / 5.0)) + 32);
        int tempInF = (int) tempInK;

        return tempInF;
    }
}
