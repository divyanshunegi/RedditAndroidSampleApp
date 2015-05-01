package apitest.reddit.com.redditapp.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import apitest.reddit.com.redditapp.R;
import apitest.reddit.com.redditapp.utilities.Utils;

/**
 * Created by divyanshunegi on 4/30/15.
 */

public class WebviewFragment extends android.support.v4.app.Fragment implements SwipeRefreshLayout.OnRefreshListener {


    public static WebView webView;
    private SwipeRefreshLayout swipeRefresh;

    public WebviewFragment() {
        // Required empty public constructor
    }

    public static WebviewFragment newInstance() {
        WebviewFragment activities = new WebviewFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_webview, container, false);
        //TODO setup webview for mobile reddit website

        swipeRefresh = (SwipeRefreshLayout) rootView.findViewById(R.id.web_swipe_container);
        swipeRefresh.setOnRefreshListener(this);
        swipeRefresh.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        webView = (WebView) rootView.findViewById(R.id.redditWebview);
        startWebsite(Utils.WEBSITE_URL);
        return rootView;
    }

    private void startWebsite(String url){
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
            public void onLoadResource (WebView view, String url) {
                //swipeRefresh.setRefreshing(true);
            }
            public void onPageFinished(WebView view, String url) {
                try{
                    swipeRefresh.setRefreshing(false);
                }catch(Exception exception){
                    exception.printStackTrace();
                }
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        webView.loadUrl(url);
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

        super.onDetach();

    }

    @Override
    public void onRefresh() {
        startWebsite(Utils.WEBSITE_URL);
    }
}
