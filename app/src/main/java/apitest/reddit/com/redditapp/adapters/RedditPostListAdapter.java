package apitest.reddit.com.redditapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import apitest.reddit.com.redditapp.R;
import apitest.reddit.com.redditapp.models.NotificationDataBean;

/**
 * Created by divyanshunegi on 5/1/15.
 */

public class RedditPostListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<NotificationDataBean> postListItem;

    public RedditPostListAdapter(Context context, ArrayList<NotificationDataBean> postListItem){
        this.context = context;
        this.postListItem = postListItem;
    }
 
    @Override
    public int getCount() {
        return postListItem.size();
    }
 
    @Override
    public Object getItem(int position) {       
        return postListItem.get(getCount() - position - 1);
    }
 
    @Override
    public long getItemId(int position) {
        return position;
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NotificationDataBean mDataList = (NotificationDataBean) this.getItem(position);

        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.element_post_list, null);
        }

        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.post_icon);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.post_title);
        TextView txtSubreddit = (TextView) convertView.findViewById(R.id.post_subreddit);

        txtTitle.setText(mDataList.getTitle());
        txtSubreddit.setText(mDataList.getSubreddit());
        Picasso.with(context).load(mDataList.getThumbnail()).into(imgIcon);

        return convertView;

    }
 
}