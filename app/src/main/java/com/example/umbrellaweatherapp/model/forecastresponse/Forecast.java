package com.example.umbrellaweatherapp.model.forecastresponse;

import android.os.Parcel;
import android.os.Parcelable;

public class Forecast implements Parcelable {

    private String day;
    private String weatherDescription;
    private int highTemp;
    private int lowTemp;
    private String weatherIconUrl;

    public Forecast() {
    }

    public Forecast(String day, String weatherDescription, int highTemp, int lowTemp, String weatherIconUrl) {
        this.day = day;
        this.weatherDescription = weatherDescription;
        this.highTemp = highTemp;
        this.lowTemp = lowTemp;
        this.weatherIconUrl = weatherIconUrl;
    }

    protected Forecast(Parcel in) {
        day = in.readString();
        weatherDescription = in.readString();
        highTemp = in.readInt();
        lowTemp = in.readInt();
        weatherIconUrl = in.readString();
    }

    public static final Creator<Forecast> CREATOR = new Creator<Forecast>() {
        @Override
        public Forecast createFromParcel(Parcel in) {
            return new Forecast(in);
        }

        @Override
        public Forecast[] newArray(int size) {
            return new Forecast[size];
        }
    };

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public int getHighTemp() {
        return highTemp;
    }

    public void setHighTemp(int highTemp) {
        this.highTemp = highTemp;
    }

    public int getLowTemp() {
        return lowTemp;
    }

    public void setLowTemp(int lowTemp) {
        this.lowTemp = lowTemp;
    }

    public String getWeatherIconUrl() {
        return weatherIconUrl;
    }

    public void setWeatherIconUrl(String weatherIconUrl) {
        this.weatherIconUrl = weatherIconUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(day);
        parcel.writeString(weatherDescription);
        parcel.writeInt(highTemp);
        parcel.writeInt(lowTemp);
        parcel.writeString(weatherIconUrl);
    }
}
