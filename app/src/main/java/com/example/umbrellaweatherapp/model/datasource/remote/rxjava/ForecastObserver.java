package com.example.umbrellaweatherapp.model.datasource.remote.rxjava;

import android.util.Log;

import com.example.umbrellaweatherapp.model.forecastresponse.ForecastResponse;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ForecastObserver implements Observer<ForecastResponse> {

    Callback callback;
    private ForecastResponse forecastResponse;

    public ForecastObserver(Callback callback) {
        this.callback = callback;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(ForecastResponse forecastResponse) {
        this.forecastResponse = forecastResponse;
    }

    @Override
    public void onError(Throwable e) {
        Log.e("TAG_FORECAST", "whatever", e);
    }

    @Override
    public void onComplete() {
        callback.getForecastResponse(forecastResponse);
    }
}
