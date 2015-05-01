package apitest.reddit.com.redditapp.manager;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;

import apitest.reddit.com.redditapp.MainActivity;
import apitest.reddit.com.redditapp.R;

/**
 * Created by divyanshunegi on 5/1/15.
 */
public class NotificationRedditManager {
    private Context ctx;

    public NotificationRedditManager(Context ctx){

        this.ctx = ctx;

    }

    public void sendNotification(String title,String subreddit){

        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Intent intent = new Intent(ctx, MainActivity.class);
        intent.putExtra("notification",true);
        PendingIntent pIntent = PendingIntent.getActivity(ctx, 0, intent, 0);
        Notification mNotification = new Notification.Builder(ctx)
                .setContentTitle(title)
                .setContentText("Subreddit: /r/"+subreddit)
                .setSmallIcon(R.mipmap.logo)
                .setContentIntent(pIntent)
                .setSound(soundUri)
                .build();

        NotificationManager notificationManager = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotification.flags |= Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(0,mNotification);
    }
}
