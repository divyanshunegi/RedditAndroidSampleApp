package apitest.reddit.com.redditapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;

import apitest.reddit.com.redditapp.utilities.Utils;

/**
 * Created by divyanshunegi on 4/30/15.
 */

public class Splash extends ActionBarActivity {

    private Handler hd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        Utils.setTitleHeader(this, R.color.reddit_color);

        hd = new Handler();
        hd.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        }, 3000);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
