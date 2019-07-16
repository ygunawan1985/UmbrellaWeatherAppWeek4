package com.example.umbrellaweatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.umbrellaweatherapp.model.datasource.remote.rxjava.Callback;
import com.example.umbrellaweatherapp.model.datasource.remote.rxjava.ForecastRepo;
import com.example.umbrellaweatherapp.model.datasource.remote.rxjava.WeatherRepo;
import com.example.umbrellaweatherapp.model.forecastresponse.Forecast;
import com.example.umbrellaweatherapp.model.forecastresponse.ForecastResponse;
import com.example.umbrellaweatherapp.model.weatherresponse.WeatherResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.example.umbrellaweatherapp.model.datasource.remote.retrofit.UrlConstants.ICON_URL;

public class CurrentWeather extends AppCompatActivity implements Callback {

    private ImageView ivWeatherIcon;
    private TextView tvCity;
    private TextView tvWeatherDescription;
    private TextView tvCurrentTemp;
    private TextView tvHighLowTemps;
    private TextView tvDateTime;
    ArrayList<Forecast> forecastList = new ArrayList<>();

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
        ForecastRepo.getForecast(this, zip, APPID);
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
        tvWeatherDescription.setText(capitalizeWord(weatherResponse.getWeather().get(0).getDescription()));
        tvCurrentTemp.setText(String.valueOf(currentTemp) + "º");
        tvDateTime.setText(strDate);
        tvHighLowTemps.setText(highandLowTemps);
    }

    @Override
    public void getForecastResponse(ForecastResponse forecastResponse) {

        SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z");
        Date date = new Date();

        for(int i = 0; i < 40; i++) {
            String strDate = formatter.format(date);
            String weatherDescription = capitalizeWord(forecastResponse.getList().get(i).getWeather().get(0).getDescription());
            float highTempInK = forecastResponse.getList().get(i).getMain().getTempMax();
            int highTemp = convertToFahrenheit(highTempInK);
            float lowTempInK = forecastResponse.getList().get(i).getMain().getTempMin();
            int lowTemp = convertToFahrenheit(lowTempInK);
            String weatherIconUrl = ICON_URL + forecastResponse.getList().get(i).getWeather().get(0).getIcon() + "@2x.png";

            forecastList.add(new Forecast(strDate, weatherDescription, highTemp, lowTemp, weatherIconUrl));
            date = add3HoursToDate(date);

            for(Forecast aForecast : forecastList){
                Log.d("TAG_FORECAST", aForecast.getDay());
                Log.d("TAG_FORECAST", aForecast.getWeatherDescription());
                Log.d("TAG_FORECAST", String.valueOf(aForecast.getHighTemp()));
                Log.d("TAG_FORECAST", String.valueOf(aForecast.getLowTemp()));
                Log.d("TAG_FORECAST", aForecast.getWeatherIconUrl());
            }
        }
    }

    public int convertToFahrenheit(float temp) {
        float tempInK = (float) (((temp - 273) * (9.0 / 5.0)) + 32);
        int tempInF = (int) tempInK;

        return tempInF;
    }

    public Date add3HoursToDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, 3);
        return calendar.getTime();
    }

    public static String capitalizeWord(String str){
        String words[] = str.split("\\s");
        String capitalizeWord = "";
        for(String word : words){
            String first = word.substring(0,1);
            String afterfirst = word.substring(1);
            capitalizeWord += first.toUpperCase() + afterfirst + " ";
        }
        return capitalizeWord.trim();
    }

    public void onClick(View view) {
        Intent forecastIntent = new Intent(this, ForecastActivity.class);
        Bundle forecastBundle = new Bundle();
        forecastBundle.putParcelableArrayList("forecastList", forecastList);
        forecastIntent.putExtras(forecastBundle);
        startActivity(forecastIntent);
    }
}
