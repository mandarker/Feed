package com.mandarker.feed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mandarker.feed.classes.OnSwipeTouchListener;
import com.mandarker.feed.classes.Restaurant;
import com.yelp.fusion.client.connection.YelpFusionApi;
import com.yelp.fusion.client.connection.YelpFusionApiFactory;

public class SwipeActivity extends AppCompatActivity {
    Intent intent = getIntent();
    YelpFusionApiFactory apiFactory = new YelpFusionApiFactory();
    YelpFusionApi yelpFusionApi;

    Restaurant[] restaurants;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipeactivity);
        try {
            yelpFusionApi = apiFactory.createAPI(getString(R.string.appID),getString(R.string.appSecret));
        }
        catch(Exception e){}

        index = 0;

        ViewGroup viewGroup = (ViewGroup) ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);

        viewGroup.setOnTouchListener(new OnSwipeTouchListener(SwipeActivity.this){
            public void onSwipeLeft(){
                index++;
            }
            public void onSwipeRight(){
                index++;
            }
        });

    }

}
