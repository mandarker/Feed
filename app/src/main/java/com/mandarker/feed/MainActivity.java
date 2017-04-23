package com.mandarker.feed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mandarker.feed.classes.OnSwipeTouchListener;
import com.yelp.fusion.client.connection.YelpFusionApi;
import com.yelp.fusion.client.connection.YelpFusionApiFactory;

public class MainActivity extends AppCompatActivity {

    YelpFusionApiFactory apiFactory = new YelpFusionApiFactory();
    //YelpFusionApi yelpFusionApi = apiFactory.createAPI(appId, appSecret);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}