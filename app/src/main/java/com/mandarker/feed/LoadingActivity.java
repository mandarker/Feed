package com.mandarker.feed;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mandarker.feed.classes.Restaurant;
import com.yelp.fusion.client.connection.YelpFusionApi;
import com.yelp.fusion.client.connection.YelpFusionApiFactory;
import com.yelp.fusion.client.models.Business;
import com.yelp.fusion.client.models.SearchResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;

public class LoadingActivity extends AppCompatActivity {
    YelpFusionApiFactory apiFactory;
    YelpFusionApi yelpFusionApi;
    ArrayList<Business> businessList;
    Map<String, String> params;
    Call<SearchResponse> call;
    SearchResponse searchResponse;
    Restaurant[] restaurants;
    int numOfResponse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        apiFactory = new YelpFusionApiFactory();

/*
        apiFactory = new YelpFusionApiFactory();

        try {
            System.out.println("before");
            yelpFusionApi = apiFactory.createAPI(getString(R.string.appID), getString(R.string.appSecret));
            System.out.println("after");
        } catch (IOException e) {}

        params = new HashMap<>();
        params.put("term", "rice");
        call = yelpFusionApi.getBusinessSearch(params);

        try {
            searchResponse = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (searchResponse != null) {
            numOfResponse = searchResponse.getTotal();
            businessList = searchResponse.getBusinesses();
            for(int i = 0; i < numOfResponse; i++)
            {
                System.out.println("Business name: " + businessList.get(i).getName());
            }
            System.out.println("Number of responses: " + numOfResponse + " ");
        }

        restaurants = new Restaurant[businessList.size()];
        Intent intent = new Intent(LoadingActivity.this, SwipeActivity.class);

        for (int i = 0; i < restaurants.length; i++){
            restaurants[i].setClosed(businessList.get(i).getIsClosed());
            restaurants[i].setDistance((float) businessList.get(i).getDistance());
            restaurants[i].setLocation(businessList.get(i).getLocation().getAddress1(), businessList.get(i).getLocation().getAddress2(),
                    businessList.get(i).getLocation().getAddress3(), businessList.get(i).getLocation().getCity(), businessList.get(i).getLocation().getState(), businessList.get(i).getLocation().getZipCode());
            restaurants[i].setName(businessList.get(i).getName());
            restaurants[i].setPhone(businessList.get(i).getPhone());
            restaurants[i].setPrice(businessList.get(i).getPrice());
            restaurants[i].setRating((float)businessList.get(i).getRating());
            restaurants[i].setReviewCount(businessList.get(i).getReviewCount());
            restaurants[i].setUrl(businessList.get(i).getUrl());

            intent.putExtra("restaurant" + i, restaurants[i]);
        }

        intent.putExtra("amount", numOfResponse);

        startActivity(intent);
*/
    }
}
