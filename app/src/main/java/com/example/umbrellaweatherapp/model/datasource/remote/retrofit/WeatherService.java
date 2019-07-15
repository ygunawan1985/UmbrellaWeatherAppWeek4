package com.example.umbrellaweatherapp.model.datasource.remote.retrofit;

import com.example.umbrellaweatherapp.model.weatherresponse.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.example.umbrellaweatherapp.model.datasource.remote.retrofit.UrlConstants.PATH;
import static com.example.umbrellaweatherapp.model.datasource.remote.retrofit.UrlConstants.QUERY_APPID;
import static com.example.umbrellaweatherapp.model.datasource.remote.retrofit.UrlConstants.QUERY_ZIP;

public interface WeatherService {

    @GET(PATH)
    Call<WeatherResponse> getWeatherResponse(
            @Query(QUERY_ZIP)String zip,
            @Query(QUERY_APPID)String APPID);
}