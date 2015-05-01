package apitest.reddit.com.redditapp.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import apitest.reddit.com.redditapp.R;
import apitest.reddit.com.redditapp.adapters.RedditPostListAdapter;
import apitest.reddit.com.redditapp.manager.RedditManager;
import apitest.reddit.com.redditapp.models.NotificationDataBean;
import apitest.reddit.com.redditapp.utilities.Utils;

/**
 * Created by divyanshunegi on 4/30/15.
 */

public class ApiFragment extends android.support.v4.app.Fragment {

    private ListView postList;
    private RedditPostListAdapter redditPostListAdapter;
    private RedditManager redditManager;
    private RedditManager.RedditPostListener redditPostListener;

    public ApiFragment() {
        // Required empty public constructor
    }

    public static ApiFragment newInstance() {
        ApiFragment activities = new ApiFragment();
        return activities;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_api, container, false);
        redditManager = RedditManager.getInstance(getActivity());
        redditPostListener = new RedditManager.RedditPostListener() {
            @Override
            public void onNewPost(List<NotificationDataBean> notificationDataBeanList) {
                redditPostListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String error) {

            }

            @Override
            public void onFirstRun() {
                redditPostListAdapter.notifyDataSetChanged();
            }
        };

        redditManager.setRedditPostListener(redditPostListener);
        postList = (ListView) rootView.findViewById(R.id.list_redditPosts);
        redditPostListAdapter = new RedditPostListAdapter(getActivity(),Utils.notificationDataBeanArrayList);
        postList.setAdapter(redditPostListAdapter);

        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        redditManager.removeRedditPostListener(redditPostListener);
        super.onDetach();

    }

}
