package com.mandarker.feed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mandarker.feed.classes.Restaurant;

public class RestaurantActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        Restaurant restaurant;

        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null){
            restaurant = bundle.getParcelable("restaurant");
        }
    }
}
