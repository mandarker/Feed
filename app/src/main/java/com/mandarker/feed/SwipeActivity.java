package com.mandarker.feed;


import android.content.Context;
import android.os.AsyncTask;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import com.mandarker.feed.classes.OnSwipeTouchListener;
import com.mandarker.feed.classes.Restaurant;

import com.squareup.picasso.Picasso;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

public class SwipeActivity extends AppCompatActivity {

    private int index;
    Restaurant[] restaurants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipeactivity);

        index = 0;

        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            restaurants = new Restaurant[bundle.getInt("amount")];
            for (int i = 0; i < restaurants.length; i++) {
                restaurants[i] = bundle.getParcelable("restaurant" + i);
                System.out.println(restaurants[i].getPictureUrl());
            }
            index = bundle.getInt("index");
        }

        ImageView image = (ImageView) findViewById(R.id.imageView);
        Picasso.with(this).load(restaurants[index].getPictureUrl()).into(image);
    }

    //yes is pressed
    public void yesIsPressed(View view) {
        Intent intent = new Intent(SwipeActivity.this, RestaurantActivity.class);
        intent.putExtra("restaurant", restaurants[index]);

        startActivity(intent);
    }

    //no is pressed
    public void noIsPressed(View view) {
        if (index < restaurants.length - 1) {
            index++;
            Intent intent = SwipeActivity.this.getIntent();
            intent.putExtra("index", index);

            for (int i = 0; i < restaurants.length; i++) {
                intent.putExtra("restaurant" + i, restaurants[i]);
            }

            intent.putExtra("amount", restaurants.length);

            startActivity(intent);

            finish();
        }
        else{
            Context context = getApplicationContext();
            CharSequence text = "You have run out of no's!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }
}

