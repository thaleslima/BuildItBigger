package com.udacity.gradle.builditbigger.data;

import android.os.AsyncTask;

import com.appspot.joke_api.jokeApi.JokeApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.BuildConfig;

import java.io.IOException;

public class JokeEndpoint extends AsyncTask<Void, Void, String> {
    private EndpointListener listener;
    private JokeApi jokeApiService;

    public interface EndpointListener {
        void onResultReceived(String result);
    }

    public JokeEndpoint(EndpointListener listener) {
        this.listener = listener;

        final JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(),
                new AndroidJsonFactory(), null).setRootUrl(BuildConfig.ENDPOINT_URL)
                .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                    @Override
                    public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                        abstractGoogleClientRequest.setDisableGZipContent(true);
                    }
                });
        jokeApiService = builder.build();
    }

    @Override
    protected String doInBackground(Void... params) {
        try {
            return jokeApiService.getJoke().execute().getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if (listener != null) {
            listener.onResultReceived(result);
        }
    }
}
