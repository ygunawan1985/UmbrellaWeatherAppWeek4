
package com.example.umbrellaweatherapp.model.forecastresponse;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rain implements Parcelable
{

    @SerializedName("3h")
    @Expose
    private Float _3h;
    public final static Parcelable.Creator<Rain> CREATOR = new Creator<Rain>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Rain createFromParcel(Parcel in) {
            return new Rain(in);
        }

        public Rain[] newArray(int size) {
            return (new Rain[size]);
        }

    }
    ;

    protected Rain(Parcel in) {
        this._3h = ((Float) in.readValue((Float.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Rain() {
    }

    /**
     * 
     * @param _3h
     */
    public Rain(Float _3h) {
        super();
        this._3h = _3h;
    }

    public Float get3h() {
        return _3h;
    }

    public void set3h(Float _3h) {
        this._3h = _3h;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(_3h);
    }

    public int describeContents() {
        return  0;
    }

}
