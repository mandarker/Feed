package com.mandarker.feed;


import android.os.AsyncTask;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.EditText;

import com.mandarker.feed.classes.OnSwipeTouchListener;
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
import retrofit2.Response;

public class SwipeActivity extends AppCompatActivity {
    Intent intent = getIntent();
    YelpFusionApiFactory apiFactory = new YelpFusionApiFactory();
    YelpFusionApi yelpFusionApi;

    Map<String, String> params;
    Call<SearchResponse> call;
    SearchResponse searchResponse;
    ArrayList<Business> businessList;
    int numOfResponse;

    Restaurant[] restaurants;
    private int index;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipeactivity);
        try {
            yelpFusionApi = apiFactory.createAPI(getString(R.string.appID), getString(R.string.appSecret));
        } catch (Exception e) {
        }

        index = 0;

        ViewGroup viewGroup = (ViewGroup) ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);

        viewGroup.setOnTouchListener(new OnSwipeTouchListener(SwipeActivity.this){
            public void onSwipeLeft(){
                index++;
            }
            public void onSwipeRight(){
                index++;

                Intent intent = new Intent(SwipeActivity.this, RestaurantActivity.class);
                intent.putExtra("restaurant", restaurants[index]);

                startActivity(intent);
            }
        });

        params = new HashMap<>();
        params.put("term", "rice");
        new FetchData().execute();
    }

    public class FetchData extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {
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
            return null;
        }
    }

}

