package com.udacity.gradle.builditbigger.paid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.android.displayjoke.DisplayJokeActivity;
import com.udacity.gradle.builditbigger.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private ProgressBar mProgressBar = null;
    public String loadJoke = null;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        // Set onClickListener for the button
        Button button = (Button) root.findViewById(R.id.joke_button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mProgressBar.setVisibility(View.VISIBLE);
                getJoke();
            }
        });

        mProgressBar = (ProgressBar) root.findViewById(R.id.joke_progress_bar);
        mProgressBar.setVisibility(View.GONE);
        return root;
    }

    public void getJoke(){
        new EndpointsAsyncTask().execute(this);
    }

    public void displayJoke(){
        Context context = getActivity();
        Intent intent = new Intent(context, DisplayJokeActivity.class);
        intent.putExtra(DisplayJokeActivity.JOKE_KEY, loadJoke);
        //Toast.makeText(context, loadJoke, Toast.LENGTH_LONG).show();
        context.startActivity(intent);
        mProgressBar.setVisibility(View.GONE);
    }
}
