package com.mandarker.feed;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
    YelpFusionApiFactory apiFactory = new YelpFusionApiFactory();
    YelpFusionApi yelpFusionApi;
    Map<String, String> params;
    Call<SearchResponse> call;
    SearchResponse searchResponse;
    ArrayList<Business> businessList;
    int numOfResponse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            yelpFusionApi = apiFactory.createAPI(getString(R.string.appID), getString(R.string.appSecret));
        } catch (Exception e) {
        }
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

