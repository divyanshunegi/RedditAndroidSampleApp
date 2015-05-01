package apitest.reddit.com.redditapp;

import android.app.Application;
import android.content.Intent;

import apitest.reddit.com.redditapp.service.PollingService;
import apitest.reddit.com.redditapp.utilities.Utils;

/**
 * Created by divyanshunegi on 4/30/15.
 */

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        startPollingService();
    }


    private void startPollingService()
    {
        if(!Utils.isMyServiceRunning(getApplicationContext(),PollingService.class)){
            startService(new Intent(this, PollingService.class));
        }
    }
}
