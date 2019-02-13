package com.example.android.displayjoke;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.android.displayjoke.databinding.ActivityDisplayJokeBinding;

public class DisplayJokeActivity extends AppCompatActivity {
    public static final String JOKE_KEY = "joke";
    private ActivityDisplayJokeBinding activityDisplayJokeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_joke);
        activityDisplayJokeBinding = DataBindingUtil.setContentView(DisplayJokeActivity.this, R.layout.activity_display_joke);
        String joke = "";
        Intent intent = getIntent();
        if (intent.hasExtra(JOKE_KEY)) {
            joke = intent.getStringExtra(JOKE_KEY);
        }

        if (!joke.isEmpty()) {
            activityDisplayJokeBinding.jokeDisplayTv.setText(joke);
        }
    }

}
