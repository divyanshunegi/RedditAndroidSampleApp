package apitest.reddit.com.redditapp.manager;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import apitest.reddit.com.redditapp.models.NotificationDataBean;
import apitest.reddit.com.redditapp.server.RedditAPIClient;
import apitest.reddit.com.redditapp.server.model.HotPostBean;
import apitest.reddit.com.redditapp.utilities.Utils;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by divyanshunegi on 4/30/15.
 */

public class RedditManager {

    private Context ctx;
    private ArrayList<RedditPostListener> redditPostListenerList;
    private static RedditManager _instance;
    private ArrayList<String> postIds;
    private NotificationDataBean notificationDataBean;
    public static boolean isStarted = false;

    private RedditManager(Context ctx) {

        this.ctx = ctx;
        redditPostListenerList = new ArrayList<>();
        postIds = new ArrayList<>();
    }

    public static RedditManager getInstance(Context ctx){
        if(_instance==null){
            _instance = new RedditManager(ctx);
        }
        return _instance;
    }

    public void getRedditPostFromREST(){

        RedditAPIClient redditAPIClient = new RedditAPIClient();
        redditAPIClient.getApi().getHotPost(new Callback<HotPostBean>() {


            @Override
            public void success(HotPostBean hotPostBean, Response response) {

                ArrayList<NotificationDataBean> notificationDataBeans = new ArrayList<>();

                for(int i=0;i<hotPostBean.getData().getChildren().size();i++){

                    String title = hotPostBean.getData().getChildren().get(i).getData().getTitle();
                    String thumbnail = hotPostBean.getData().getChildren().get(i).getData().getThumbnail();
                    String url = hotPostBean.getData().getChildren().get(i).getData().getUrl();
                    String subreddit = hotPostBean.getData().getChildren().get(i).getData().getSubreddit();
                    String id = hotPostBean.getData().getChildren().get(i).getData().getId();


                    if(!isStarted){

                        postIds.add(url);
                        notificationDataBeans.add(new NotificationDataBean(id, title, thumbnail, url, subreddit));
                        notifyFirstRun();

                    }else if (checkOldPosts(url)){

                        postIds.add(url);
                        notificationDataBeans.add(new NotificationDataBean(id,title, thumbnail, url, subreddit));


                    }

                  }

                if(notificationDataBeans.size()>0){
                    Utils.notificationDataBeanArrayList.clear();
                    Utils.notificationDataBeanArrayList.addAll(notificationDataBeans);
                    notifyNewPost(notificationDataBeans);
                    isStarted = true;
                }else{
                    notifyError("No Change");
                }


                }

            @Override
            public void failure(RetrofitError error) {

                notifyError(error.toString());
                System.out.println(error);

            }
        });
    }

    private boolean checkOldPosts(String url) {

        if(postIds.contains(url)){
//            if(postIds.size()>5){
//                for(int i=5;i<postIds.size();i++){
//                    postIds.remove(id);
//                }
//            }
            return false;
        }else{
            return true;
        }

    }

    public void setRedditPostListener(RedditPostListener redditPostListener){
        redditPostListenerList.add(redditPostListener);
    }

    public void removeRedditPostListener(RedditPostListener redditPostListener){
        redditPostListenerList.remove(redditPostListener);
    }


    private void notifyNewPost(List<NotificationDataBean> notificationDataBeanList){
        for(RedditPostListener redditPostListener :redditPostListenerList){
            redditPostListener.onNewPost(notificationDataBeanList);
        }
    }

    private void notifyFirstRun(){
        for(RedditPostListener redditPostListener :redditPostListenerList){
            redditPostListener.onFirstRun();
        }
    }

    private void notifyError(String error){
        for(RedditPostListener redditPostListener :redditPostListenerList){
            redditPostListener.onError(error);
        }
    }


    public interface RedditPostListener{
        void onNewPost(List<NotificationDataBean> notificationDataBeanList);
        void onError(String error);
        void onFirstRun();
    }

}
