package com.mandarker.feed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.mandarker.feed.classes.OnSwipeTouchListener;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user taps the Enter button in the home scree */
    public void searchPressed(View view) {
        Intent intent = new Intent(this, LoadingActivity.class);

        //Stores category input(searchEditText) into a string variable(category)
        EditText editText = (EditText) findViewById(R.id.searchEditText);
        String category = editText.getText().toString();


        //start new activity
        startActivity(intent);
    }
}