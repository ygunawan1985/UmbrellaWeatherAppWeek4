
package com.example.umbrellaweatherapp.model.forecastresponse;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class City implements Parcelable
{

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("coord")
    @Expose
    private Coord coord;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("timezone")
    @Expose
    private Integer timezone;
    public final static Parcelable.Creator<City> CREATOR = new Creator<City>() {


        @SuppressWarnings({
            "unchecked"
        })
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        public City[] newArray(int size) {
            return (new City[size]);
        }

    }
    ;

    protected City(Parcel in) {
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.coord = ((Coord) in.readValue((Coord.class.getClassLoader())));
        this.country = ((String) in.readValue((String.class.getClassLoader())));
        this.timezone = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public City() {
    }

    /**
     * 
     * @param coord
     * @param timezone
     * @param name
     * @param country
     */
    public City(String name, Coord coord, String country, Integer timezone) {
        super();
        this.name = name;
        this.coord = coord;
        this.country = country;
        this.timezone = timezone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getTimezone() {
        return timezone;
    }

    public void setTimezone(Integer timezone) {
        this.timezone = timezone;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(name);
        dest.writeValue(coord);
        dest.writeValue(country);
        dest.writeValue(timezone);
    }

    public int describeContents() {
        return  0;
    }

}
