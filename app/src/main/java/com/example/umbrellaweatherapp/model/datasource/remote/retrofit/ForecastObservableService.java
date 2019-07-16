package com.example.umbrellaweatherapp.model.datasource.remote.retrofit;

import com.example.umbrellaweatherapp.model.forecastresponse.ForecastResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.example.umbrellaweatherapp.model.datasource.remote.retrofit.UrlConstants.FORECAST_PATH;
import static com.example.umbrellaweatherapp.model.datasource.remote.retrofit.UrlConstants.QUERY_APPID;
import static com.example.umbrellaweatherapp.model.datasource.remote.retrofit.UrlConstants.QUERY_ZIP;

public interface ForecastObservableService {

    @GET(FORECAST_PATH)
    Observable<ForecastResponse> getForecastResponse(
            @Query(QUERY_ZIP)String zip,
            @Query(QUERY_APPID)String APPID);

}
