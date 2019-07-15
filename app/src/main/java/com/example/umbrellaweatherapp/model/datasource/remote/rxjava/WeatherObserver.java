package com.example.umbrellaweatherapp.model.datasource.remote.rxjava;

import android.util.Log;

import com.example.umbrellaweatherapp.model.weatherresponse.WeatherResponse;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class WeatherObserver implements Observer<WeatherResponse> {

    Callback callback;
    private WeatherResponse weatherResponse;

    public WeatherObserver(Callback callback) {
        this.callback = callback;
    }

    @Override
    public void onSubscribe(Disposable d) {
        //Do what is in here when we a object starts listening for emitting

    }

    @Override
    public void onNext(WeatherResponse weatherResponse) {
        //Do this when we get an result
        //Log.d("TAG_RX", weatherResponse.getName());
        this.weatherResponse = weatherResponse;
    }

    @Override
    public void onError(Throwable e) {
        //Do this if something goes wrong
    }

    @Override
    public void onComplete() {
        callback.getWeatherResponse(weatherResponse);
    }

}
