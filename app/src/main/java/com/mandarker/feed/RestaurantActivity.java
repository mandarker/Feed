package com.mandarker.feed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.mandarker.feed.classes.Restaurant;
import com.squareup.picasso.Picasso;

public class RestaurantActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        //Restaurant restaurant;
        Restaurant restaurant = new Restaurant("Morgan's Mackerals", (float) 0.0, 2, "$$$$$$", "+5555555555", true, "nowhere", 0, "https://www.yelp.com/biz/steep-creamery-and-tea-san-francisco");

        /*
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null){
            restaurant = bundle.getParcelable("restaurant");
        }
        */
        
        image = (ImageView) findViewById(R.id.imageView2);
        Picasso.with(this).load(restaurant.getImages().get(1)).into(image);
        image = (ImageView) findViewById(R.id.imageView3);
        Picasso.with(this).load(restaurant.getImages().get(2)).into(image);
        image = (ImageView) findViewById(R.id.imageView4);
        Picasso.with(this).load(restaurant.getImages().get(3)).into(image);

        TextView text = (TextView) findViewById(R.id.name);
        text.setText(restaurant.getName());
        text = (TextView) findViewById(R.id.price);
        text.setText(restaurant.getPrice());
        text = (TextView) findViewById(R.id.rating);
        text.setText("" + restaurant.getRating());
        text = (TextView) findViewById(R.id.address);
        text.setText("" + restaurant.getLocation());
        text = (TextView) findViewById(R.id.phone);
        text.setText("" + restaurant.getPhone());
    }
}
