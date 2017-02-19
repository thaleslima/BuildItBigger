package com.udacity.gradle.builditbigger.data;

import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static junit.framework.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AsyncTaskResponseTest {

    @org.junit.Test
    public void asyncTaskResponse_ResponseIsNotNull() throws InterruptedException {
        final CountDownLatch signal = new CountDownLatch(1);

        final JokeEndpoint jokeEndpoint = new JokeEndpoint(new JokeEndpoint.EndpointListener() {
            @Override
            public void onResultReceived(String result) {
                assertNotNull("Response result should not be Null", result);
                signal.countDown();
            }
        });

        jokeEndpoint.execute();
        signal.await();
    }
}
