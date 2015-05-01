package apitest.reddit.com.redditapp.manager;

import android.content.Context;

import java.util.ArrayList;

import apitest.reddit.com.redditapp.server.model.HotPostBean;
import apitest.reddit.com.redditapp.models.NotificationDataBean;
import apitest.reddit.com.redditapp.server.RedditAPIClient;
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

                for(int i=0;i<5;i++){
                    String title = hotPostBean.getData().getChildren().get(i).getData().getTitle();
                    String thumbnail = hotPostBean.getData().getChildren().get(i).getData().getThumbnail();
                    String url = hotPostBean.getData().getChildren().get(i).getData().getUrl();
                    String subreddit = hotPostBean.getData().getChildren().get(i).getData().getSubreddit();
                    String id = hotPostBean.getData().getChildren().get(i).getData().getId();

                    if(postIds.isEmpty()){
                        postIds.add(id);
                        notifyNewPost(new NotificationDataBean(title, thumbnail, url, subreddit));
                    }else if(checkOldPosts(id)){
                        postIds.add(id);
                        notifyNewPost(new NotificationDataBean(title, thumbnail, url, subreddit));
                     }else{
                        notifyError("no change");
                    }
                  }

            }

            @Override
            public void failure(RetrofitError error) {

                notifyError(error.toString());
                System.out.println(error);

            }
        });
    }

    private boolean checkOldPosts(String id) {

        if(postIds.contains(id)){
            if(postIds.size()>5){
                for(int i=5;i<postIds.size();i++){
                    postIds.remove(id);
                }
            }
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


    private void notifyNewPost(NotificationDataBean notificationDataBean){
        for(RedditPostListener redditPostListener :redditPostListenerList){
            redditPostListener.onNewPost(notificationDataBean);
        }
    }

    private void notifyError(String error){
        for(RedditPostListener redditPostListener :redditPostListenerList){
            redditPostListener.onError(error);
        }
    }


    public interface RedditPostListener{
        void onNewPost(NotificationDataBean notificationDataBean);
        void onError(String error);
    }

}
