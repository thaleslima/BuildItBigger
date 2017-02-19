package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.jokedetail.JokeActivity;
import com.udacity.gradle.builditbigger.data.JokeEndpoint;

public class MainActivityBaseFragment extends Fragment implements JokeEndpoint.EndpointListener {
    private JokeEndpoint jokeEndpoint;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        final Button tellJokeButton = (Button) root.findViewById(R.id.tell_joke_view);
        tellJokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tellJoke();
            }
        });

        progressBar = (ProgressBar) root.findViewById(R.id.progress_bar);

        return root;
    }

    private void tellJoke() {
        showProgress();
        jokeEndpoint = new JokeEndpoint(this);
        jokeEndpoint.execute();
    }

    @Override
    public void onResultReceived(String result) {
        hideProgress();
        openJake(result);
    }

    protected void openJake(String jake) {
        if (null == jake) {
            showErrorMessage();
        } else {
            JokeActivity.navigate(this.getActivity(), jake);
        }
    }

    private void showErrorMessage() {
        Toast.makeText(this.getContext(), R.string.error_message, Toast.LENGTH_LONG).show();
    }

    protected void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    protected void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (jokeEndpoint != null) {
            jokeEndpoint.cancel(true);
        }
    }
}
