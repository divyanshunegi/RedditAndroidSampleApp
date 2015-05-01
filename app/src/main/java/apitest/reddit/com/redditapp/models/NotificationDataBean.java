package apitest.reddit.com.redditapp.models;

/**
 * Created by divyanshunegi on 5/1/15.
 */
public class NotificationDataBean {

    private String id;
    private String title;
    private String thumbnail;
    private String url;
    private String subreddit;



    public NotificationDataBean(String id, String title, String thumbnail, String url, String subreddit) {
        this.id = id;
        this.title = title;
        this.thumbnail = thumbnail;
        this.url = url;
        this.subreddit = subreddit;
    }

    public NotificationDataBean() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSubreddit() {
        return subreddit;
    }

    public void setSubreddit(String subreddit) {
        this.subreddit = subreddit;
    }
}
