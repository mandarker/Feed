package com.mandarker.feed.classes;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mandarker on 4/22/2017.
 */

public class Restaurant implements Parcelable {

    private String name;
    private float rating;
    private String phone;
    private String location;
    private String pictureUrl;

    public Restaurant(){

    }

    public Restaurant(String name, float rating, String phone, String location, String pictureUrl) {
        this.name = name;
        this.rating = rating;
        this.phone = phone;
        this.location = location;
        this.pictureUrl = pictureUrl;
    }

    protected Restaurant(Parcel in) {
        String[] strings = new String[4];
        in.readStringArray(strings);

        name = strings[0];
        rating = in.readFloat();
        phone = strings[1];
        location = strings[2];
        pictureUrl = strings[3];
    }

    public static final Creator<Restaurant> CREATOR = new Creator<Restaurant>() {
        @Override
        public Restaurant createFromParcel(Parcel in) {
            return new Restaurant(in);
        }

        @Override
        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringArray(new String[]{this.name, this.phone, this.location, this.pictureUrl});
        parcel.writeFloat(this.rating);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setLocation(String address, String city, String state, String zipCode) {
        String location = "";
        
        if (!address.equals(""))
            location += address + " ";
        if (!city.equals(""))
            location += city + ", ";
        if (!state.equals(""))
            location += state + " ";
        if (!zipCode.equals(""))
            location += zipCode + " ";
        
        this.location = location;
    }

    public String getPictureUrl(){
        return pictureUrl;
    }

    public void setPictureUrl(String str) {
        this.pictureUrl = str;
    }


}
