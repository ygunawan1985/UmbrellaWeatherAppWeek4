package com.example.umbrellaweatherapp.model.datasource.remote.rxjava;

import com.example.umbrellaweatherapp.model.weatherresponse.WeatherResponse;

public interface Callback {
    void getWeatherResponse(WeatherResponse weatherResponse);
}
