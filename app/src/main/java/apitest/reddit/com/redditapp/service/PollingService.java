package apitest.reddit.com.redditapp.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import apitest.reddit.com.redditapp.manager.NotificationRedditManager;
import apitest.reddit.com.redditapp.manager.RedditManager;
import apitest.reddit.com.redditapp.models.NotificationDataBean;
import apitest.reddit.com.redditapp.utilities.Utils;

/**
 * Created by divyanshunegi on 4/30/15.
 */
public class PollingService extends Service {

    private final long INTERVAL = 1000*60*Utils.TIME_IN_MINUTES_FOR_POLLING; // periodic interval to check in Minutes
    private final String TAG = PollingService.class.getSimpleName();

    private static Context ctx = null;
    private Handler pollingHandler;
    private Runnable pollingRunnable;
    private RedditManager redditManager;
    private RedditManager.RedditPostListener redditPostListener;
    private NotificationRedditManager notificationManager;

    @Override
    public void onDestroy() {
        Log.i(TAG, "Stopping service 'pollingService'");
        pollingHandler.removeCallbacks(pollingRunnable);
        redditManager.removeRedditPostListener(redditPostListener);
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "Starting service 'pollingService'");
        ctx = this;
        notificationManager = new NotificationRedditManager(ctx);
        pollingHandler = new Handler();
        redditManager = RedditManager.getInstance(this);
        redditPostListener = new RedditManager.RedditPostListener() {

            @Override
            public void onNewPost(NotificationDataBean notificationDataBean) {
                notificationManager.sendNotification(notificationDataBean.getTitle(),notificationDataBean.getSubreddit());
                System.out.println(notificationDataBean.getSubreddit()+"-"+notificationDataBean.getTitle());
                Utils.notificationDataBeanArrayList.add(notificationDataBean);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getApplicationContext(),error,Toast.LENGTH_SHORT).show();
            }
        };
        redditManager.setRedditPostListener(redditPostListener);

        pollingRunnable = new Runnable() {
            @Override
            public void run() {
                //TODO make polling manager
                //TODO parse GSON in polling manager and run it in every INTERVAL
                redditManager.getRedditPostFromREST();
                pollingHandler.postDelayed(this,INTERVAL);
            }
        };

        pollingHandler.postDelayed(pollingRunnable, 2000);

        return Service.START_STICKY;
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
