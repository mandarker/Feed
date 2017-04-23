package com.mandarker.feed;

import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

import com.mandarker.feed.classes.Restaurant;
import com.mandarker.feed.classes.RestaurantImageParser;
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
    RestaurantImageParser imgParser;

    //fade stuff
    Handler handler;
    TextView mSwitcher;

    Animation in;
    Animation out;

    int fadeCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);


        //fade in fade out text on loading screen
        fadeCount = 0;

        handler = new Handler();

        mSwitcher = (TextView) findViewById(R.id.textView3);
        mSwitcher.setText("old text");

        in = new AlphaAnimation(0.0f, 1.0f);
        in.setDuration(1500);

        out = new AlphaAnimation(1.0f, 0.0f);
        out.setDuration(1500);
        out.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationEnd(Animation animation) {
                fadeCount++;
                if (fadeCount == 3){
                    mSwitcher.setText("");
                    Intent i = new Intent(getApplication() ,  SwipeActivity.class);
                    startActivity(i);
                }
                else {
                    if (fadeCount == 1) {
                        mSwitcher.setText("Preparing selection");
                    } else {
                        mSwitcher.setText("Please wait...");
                    }

                    mSwitcher.startAnimation(in);
                    handler.postDelayed(mFadeOut, 1500);
                }
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationStart(Animation arg0) {
                // TODO Auto-generated method stub

            }
        });

        //mSwitcher.startAnimation(out);
        mSwitcher.setText("Please wait...");
        mSwitcher.startAnimation(in);

       /*
        mSwitcher.startAnimation(out);
        mSwitcher.setText("Text 2.");
        mSwitcher.startAnimation(in);
        */

        handler.postDelayed(mFadeOut, 1500);

        //before stuff
        restaurants = new Restaurant[40];

        new FetchData().execute();
    }

    //fade in out
    private Runnable mFadeOut =new Runnable(){

        @Override
        public void run() {
            //Speed up the last fade-out so that the Activity opens faster
            if (fadeCount == 2){
                out.setDuration(1500);
            }
            mSwitcher.startAnimation(out);
        }
    };

    class FetchData extends AsyncTask<String,String,String> {

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }


        @Override
        protected String doInBackground(String... strings) {
            String term = "";
            Bundle bundle = LoadingActivity.this.getIntent().getExtras();
            if (bundle != null) {
                term = bundle.getString("category");
            }

            apiFactory = new YelpAPIFactory(getString(R.string.consumerKey), getString(R.string.consumerSecret), getString(R.string.token), getString(R.string.tokenSecret));
            yelpAPI = apiFactory.createAPI();
            params = new HashMap<>();
            params.put("term", term);
            CoordinateOptions coordinate = CoordinateOptions.builder().latitude(37.44).longitude(-122.23).build();
            call = yelpAPI.search(coordinate, params);
            imgParser = new RestaurantImageParser();

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
                Intent intent = new Intent(LoadingActivity.this, SwipeActivity.class);
                Restaurant temp;
                for (int i = 0; i < numOfResponse; i++) {
                   temp = new Restaurant();
                   temp.setName(businessList.get(i).name());
                   temp.setPhone(businessList.get(i).displayPhone());
                   temp.setRating((float)(double)businessList.get(i).rating());
                   temp.setLocation(businessList.get(i).location().address().get(0), businessList.get(i).location().city(), businessList.get(i).location().city(), businessList.get(i).location().postalCode());temp.setPictureUrl(businessList.get(i).imageUrl()); //temp.setPictureUrl(imgParser.getOriginal(businessList.get(i).imageUrl()));
                    /* String url = businessList.get(i).url();

                    for (int j = 0; j < url.length(); j++){
                        if (url.charAt(j) == '?')
                            url = url.substring(0, j);
                    }

                    //temp.setUrl(url);
                    */
                   restaurants[i] = temp;

                   intent.putExtra("restaurant" + i, restaurants[i]);
                }

                intent.putExtra("amount", numOfResponse);

                startActivity(intent);

                finish();

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
