package apitest.reddit.com.redditapp.server.model;

/**
 * Created by divyanshunegi on 4/30/15.
 */
public class HotPostBean {

    private String kind;
    private DataBean data;

    public HotPostBean(String kind, DataBean data) {
        this.kind = kind;
        this.data = data;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }
}
