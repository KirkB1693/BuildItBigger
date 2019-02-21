package com.udacity.gradle.builditbigger;

import android.support.test.runner.AndroidJUnit4;

import com.udacity.gradle.builditbigger.free.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.free.MainActivityFragment;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class EndpointsAsyncTaskTestFree {

    @Test
    public void testJokeNonEmpty() throws Exception {
        // Start a CountDownLatch set to 1
        final CountDownLatch latch = new CountDownLatch(1);
        MainActivityFragment mainActivityFragment = new MainActivityFragment();
        EndpointsAsyncTask testAsyncTask = new EndpointsAsyncTask() {
            @Override
            protected void onPostExecute(String joke) {
                // check that joke is not empty or null
                assertThat(joke, not(isEmptyOrNullString()));
                // decrement latch now that AsyncTask has finished executing
                latch.countDown();
            }
        };
        testAsyncTask.execute(mainActivityFragment);
        // stop and wait for latch to equal zero before proceding
        latch.await();
    }
}
