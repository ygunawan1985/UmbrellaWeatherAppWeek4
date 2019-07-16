package com.example.umbrellaweatherapp.model.datasource.remote.rxjava;

import com.example.umbrellaweatherapp.model.forecastresponse.ForecastResponse;
import com.example.umbrellaweatherapp.model.weatherresponse.WeatherResponse;

public interface Callback {
    void getWeatherResponse(WeatherResponse weatherResponse);

    void getForecastResponse(ForecastResponse forecastResponse);
}
