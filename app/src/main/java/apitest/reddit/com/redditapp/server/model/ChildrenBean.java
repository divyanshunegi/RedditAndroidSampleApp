package apitest.reddit.com.redditapp.server.model;

/**
 * Created by divyanshunegi on 5/1/15.
 */
public class ChildrenBean {

    private String kind;
    private ChildrenDataBean data;

    public ChildrenBean(String kind, ChildrenDataBean data) {
        this.kind = kind;
        this.data = data;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public ChildrenDataBean getData() {
        return data;
    }

    public void setData(ChildrenDataBean data) {
        this.data = data;
    }
}
