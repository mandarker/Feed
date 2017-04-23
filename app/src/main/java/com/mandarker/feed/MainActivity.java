package com.mandarker.feed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user taps the Enter button in the home scree */
    public void searchPressed(View view) {
        //creates new intent to create new activity
        Intent intent = new Intent(this, SwipeActivity.class);

        //Stores category input(searchEditText) into a string variable(category)
        EditText editText = (EditText) findViewById(R.id.searchEditText);
        String category = editText.getText().toString();

        //start new activity
        startActivity(intent);
    }
}