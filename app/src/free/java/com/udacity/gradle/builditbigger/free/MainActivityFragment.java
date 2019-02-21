package com.udacity.gradle.builditbigger.free;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.android.displayjoke.DisplayJokeActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.udacity.gradle.builditbigger.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private ProgressBar mProgressBar = null;
    public String loadJoke = null;
    private PublisherInterstitialAd mPublisherInterstitialAd;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        Context context = this.getContext();

        if (context != null) {
            MobileAds.initialize(this.getContext(), getString(R.string.app_ad_id));
            mPublisherInterstitialAd = new PublisherInterstitialAd(this.getContext());
            mPublisherInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
            mPublisherInterstitialAd.loadAd(new PublisherAdRequest.Builder().build());
            mPublisherInterstitialAd.setAdListener(new AdListener() {

                @Override
                public void onAdFailedToLoad(int errorCode) {
                    // Code to be executed when an ad request fails.
                    // Get a new add
                    mPublisherInterstitialAd.loadAd(new PublisherAdRequest.Builder().build());
                }

                @Override
                public void onAdClosed() {
                    // Code to be executed when the interstitial ad is closed.
                    mProgressBar.setVisibility(View.VISIBLE);
                    getJoke();
                    mPublisherInterstitialAd.loadAd(new PublisherAdRequest.Builder().build());
                }
            });
        }
        // Set onClickListener for the button
        Button button = (Button) root.findViewById(R.id.joke_button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (mPublisherInterstitialAd.isLoaded()) {
                    mPublisherInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                    mProgressBar.setVisibility(View.VISIBLE);
                    getJoke();
                }
            }
        });

        mProgressBar = (ProgressBar) root.findViewById(R.id.joke_progress_bar);
        mProgressBar.setVisibility(View.GONE);

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);


        return root;
    }

    private void getJoke(){
        new EndpointsAsyncTask().execute(this);
    }

    public void displayJoke(){
        Context context = getActivity();
        if (context != null) {
            Intent intent = new Intent(context, DisplayJokeActivity.class);
            intent.putExtra(DisplayJokeActivity.JOKE_KEY, loadJoke);
            //Toast.makeText(context, loadJoke, Toast.LENGTH_LONG).show();
            context.startActivity(intent);
            mProgressBar.setVisibility(View.GONE);
        }
    }
}
