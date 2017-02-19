package com.example.jokedetail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {
    private static final String JOKE_EXTRA = "com.example.jokedetail.JOKE_EXTRA";

    public static void navigate(Activity activity, String joke) {
        Intent intent = new Intent(activity, JokeActivity.class);
        intent.putExtra(JOKE_EXTRA, joke);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        TextView jokeMessage = (TextView) findViewById(R.id.joke_message);

        if (getIntent().hasExtra(JOKE_EXTRA)) {
            String jokeExtra = getIntent().getStringExtra(JOKE_EXTRA);
            jokeMessage.setText(jokeExtra);
        }
    }
}
