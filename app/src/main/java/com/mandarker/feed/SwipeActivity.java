package com.mandarker.feed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yelp.fusion.client.connection.YelpFusionApi;
import com.yelp.fusion.client.connection.YelpFusionApiFactory;

public class SwipeActivity extends AppCompatActivity {
    YelpFusionApiFactory apiFactory = new YelpFusionApiFactory();
    YelpFusionApi yelpFusionApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            yelpFusionApi = apiFactory.createAPI(getString(R.string.appID),getString(R.string.appSecret));
        }
        catch(Exception e){}


    }
}
