package apitest.reddit.com.redditapp.server.model;

import java.util.List;

/**
 * Created by divyanshunegi on 4/30/15.
 */
public class DataBean {

    private String modhash;
    private String after;
    private String before;
    private List<ChildrenBean> children;

    public DataBean(String modhash, String after, String before, List<ChildrenBean> children) {
        this.modhash = modhash;
        this.after = after;
        this.before = before;
        this.children = children;
    }

    public String getModhash() {
        return modhash;
    }

    public void setModhash(String modhash) {
        this.modhash = modhash;
    }

    public String getAfter() {
        return after;
    }

    public void setAfter(String after) {
        this.after = after;
    }

    public String getBefore() {
        return before;
    }

    public void setBefore(String before) {
        this.before = before;
    }

    public List<ChildrenBean> getChildren() {
        return children;
    }

    public void setChildren(List<ChildrenBean> children) {
        this.children = children;
    }
}
