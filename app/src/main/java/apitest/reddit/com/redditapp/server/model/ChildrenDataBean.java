package apitest.reddit.com.redditapp.server.model;

/**
 * Created by divyanshunegi on 5/1/15.
 */
public class ChildrenDataBean {

    private String url;
    private String subreddit;
    private String thumbnail;
    private String title;
    private String id;

    public ChildrenDataBean(String url, String subreddit, String thumbnail, String title, String id) {
        this.url = url;
        this.subreddit = subreddit;
        this.thumbnail = thumbnail;
        this.title = title;
        this.id = id;
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

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
