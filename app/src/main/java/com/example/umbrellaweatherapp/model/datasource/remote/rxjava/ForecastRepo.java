package com.example.umbrellaweatherapp.model.datasource.remote.rxjava;

import com.example.umbrellaweatherapp.model.datasource.remote.retrofit.RetrofitHelper;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ForecastRepo {

    public static void getForecast(Callback callback, int zip, String APPID){

        new RetrofitHelper()
                .getForecastObservableService()
                .getForecastResponse(String.valueOf(zip), APPID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ForecastObserver(callback));
    }
}
