package com.example.umbrellaweatherapp.model.datasource.remote.rxjava;

import com.example.umbrellaweatherapp.model.datasource.remote.retrofit.RetrofitHelper;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class WeatherRepo {

    public static void getWeather(Callback callback, int zip, String APPID){
        new RetrofitHelper()
                .getObservableService()
                .getWeatherResponse(String.valueOf(zip), APPID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new WeatherObserver(callback));
    }
}
