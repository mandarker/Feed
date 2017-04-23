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

    private int index;
    Restaurant[] restaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipeactivity);

        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null){
            restaurant = new Restaurant[bundle.getInt("amount")];
            for (int i = 0; i < restaurant.length; i++)
                restaurant[i] = bundle.getParcelable("restaurant" + i);
        }

        index = 0;

        ViewGroup viewGroup = (ViewGroup) ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);

        viewGroup.setOnTouchListener(new OnSwipeTouchListener(SwipeActivity.this){
            public void onSwipeLeft(){
                index++;
            }
            public void onSwipeRight(){
                Intent intent = new Intent(SwipeActivity.this, RestaurantActivity.class);
                intent.putExtra("restaurant", restaurant[index]);

                startActivity(intent);
            }
        });
    }
}

