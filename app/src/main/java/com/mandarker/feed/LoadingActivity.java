package com.mandarker.feed;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mandarker.feed.classes.Restaurant;
import com.yelp.clientlib.connection.YelpAPI;
import com.yelp.clientlib.connection.YelpAPIFactory;
import com.yelp.clientlib.entities.Business;
import com.yelp.clientlib.entities.SearchResponse;
import com.yelp.clientlib.entities.options.CoordinateOptions;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

public class LoadingActivity extends AppCompatActivity {

    YelpAPIFactory apiFactory;
    YelpAPI yelpAPI;
    Map<String,String> params;
    Call<SearchResponse> call;
    Response<SearchResponse> response;
    Restaurant[] restaurants;
    ArrayList<Business> businessList;
    int numOfResponse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        new FetchData().execute();
    }

    class FetchData extends AsyncTask<String,String,String> {

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }


        @Override
        protected String doInBackground(String... strings) {
            apiFactory = new YelpAPIFactory(getString(R.string.consumerKey), getString(R.string.consumerSecret), getString(R.string.token), getString(R.string.tokenSecret));
            yelpAPI = apiFactory.createAPI();
            params = new HashMap<>();
            params.put("term", "chicken");
            CoordinateOptions coordinate = CoordinateOptions.builder().latitude(37.44).longitude(-122.23).build();
            call = yelpAPI.search(coordinate, params);
            response = null;
            try {
                response = call.execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (response != null) {
                businessList = response.body().businesses();
                numOfResponse = businessList.size();
                restaurants = new Restaurant[numOfResponse];
                restaurants[0].setName(businessList.get(0).name());
                System.out.println(restaurants[0].getName());
                /*
                for (int i = 0; i < numOfResponse; i++){
                    //restaurants[i].setClosed(businessList.get(i).isClosed());
                   // restaurants[i].setDistance((float) businessList.get(i).getDistance());
                    restaurants[i].setLocation(businessList.get(i).getLocation().getAddress1(), businessList.get(i).getLocation().getAddress2(),
                            businessList.get(i).getLocation().getAddress3(), businessList.get(i).getLocation().getCity(), businessList.get(i).getLocation().getState(), businessList.get(i).getLocation().getZipCode());
                    restaurants[i].setName(businessList.get(i).getName());
                    restaurants[i].setPhone(businessList.get(i).getPhone());
                    restaurants[i].setPrice(businessList.get(i).getPrice());
                    restaurants[i].setRating((float)businessList.get(i).getRating());
                    restaurants[i].setReviewCount(businessList.get(i).getReviewCount());
                    restaurants[i].setUrl(businessList.get(i).getUrl());
            }*/

            }
            return null;
        }
    }
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
