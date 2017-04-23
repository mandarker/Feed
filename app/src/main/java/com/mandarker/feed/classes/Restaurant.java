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
    private String price;
    private String phone;
    private boolean isClosed;
    private String location;
    private String url;
    private String pictureUrl;
    private List<String> images;

    public Restaurant(){

    }

    public Restaurant(String name, float rating, int reviewCount, String price, String phone, boolean isClosed, String location, float distance, String url) {
        this.name = name;
        this.rating = rating;
        this.price = price;
        this.phone = phone;
        this.isClosed = isClosed;
        this.location = location;
        this.url = url;
    }

    protected Restaurant(Parcel in) {
        String[] strings = new String[5];
        in.readStringArray(strings);

        boolean[] booleans = new boolean[1];
        in.readBooleanArray(booleans);

        name = strings[0];
        rating = in.readFloat();
        price = strings[1];
        phone = strings[2];
        isClosed = booleans[0];
        location = strings[3];
        url = strings[4];
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
        parcel.writeStringArray(new String[]{this.name, this.price, this.phone, this.location, this.url});
        parcel.writeBooleanArray(new boolean[]{this.isClosed});
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
        System.out.println(url);
        this.pictureUrl = url.replace("/biz", "/biz_photos");
        System.out.println(pictureUrl);
        this.pictureUrl = pictureUrl + "?tab=food";
        System.out.println(pictureUrl);

        try {
            ArrayList<String> images = RestaurantImageParser.getPictures(pictureUrl);
        }
        catch (Exception e){
            e.getStackTrace();
        }

        for (int i = 0; i < 4; i++){
            if (images.get(i) != null && !images.get(i).equals(""))
                this.images.add(images.get(i));
        }
    }

    public String getPictureUrl(){
        return pictureUrl;
    }

    public void setPictureUrl(String str) {
        this.pictureUrl = str;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
