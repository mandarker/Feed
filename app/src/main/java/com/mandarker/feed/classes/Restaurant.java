package com.mandarker.feed.classes;

/**
 * Created by mandarker on 4/22/2017.
 */

public class Restaurant {

    private String name;
    private int rating;
    private int reviewCount;
    private String price;
    private String phone;
    private boolean isClosed;
    private String location;
    private float distance;
    private String url;

    public Restaurant(String name, int rating, int reviewCount, String price, String phone, boolean isClosed, String location, float distance, String url) {
        this.name = name;
        this.rating = rating;
        this.reviewCount = reviewCount;
        this.price = price;
        this.phone = phone;
        this.isClosed = isClosed;
        this.location = location;
        this.distance = distance;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
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

    public void setLocation(String address1, String address2, String address3, String city, String state, String zipCode) {
        String location = "";
        
        if (!address1.equals(""))  
            location += address1 + " ";
        if (!address2.equals(""))
            location += address2 + " ";
        if (!address3.equals(""))
            location += address3 + ", ";
        if (!city.equals(""))
            location += city + ", ";
        if (!state.equals(""))
            location += state + " ";
        if (!zipCode.equals(""))
            location += zipCode + " ";
        
        this.location = location;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
