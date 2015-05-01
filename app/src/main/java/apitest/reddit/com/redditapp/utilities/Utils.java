package apitest.reddit.com.redditapp.utilities;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.view.WindowManager;

import java.util.ArrayList;

import apitest.reddit.com.redditapp.models.NotificationDataBean;

/**
 * Created by divyanshunegi on 4/30/15.
 */

public class Utils {

    public static String API_ROOT = "http://www.reddit.com/";
    public static String WEBSITE_URL = "http://www.reddit.com/hot/.compact";
    public static int TIME_IN_MINUTES_FOR_POLLING = 1;
    public static ArrayList<NotificationDataBean> notificationDataBeanArrayList = new ArrayList<>();

    public static void setTitleHeader(Activity act,int color){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            act.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            act.getWindow().setStatusBarColor(act.getResources().getColor(color));
        }
    }

    public static boolean isMyServiceRunning(Context ctx, Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager
                .getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public static void saveDataInApp(Context ctx,String id,String data){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(id,data);
        editor.apply();
    }

    public static String getSavedDataFromApp(Context ctx,String id){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        String name = preferences.getString(id,"");
        if(name.equalsIgnoreCase(""))
        {
            name = null;
        }
        return name;
    }
}
