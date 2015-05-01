package apitest.reddit.com.redditapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.astuetz.PagerSlidingTabStrip;

import apitest.reddit.com.redditapp.adapters.FragmentAdapter;
import apitest.reddit.com.redditapp.fragment.WebviewFragment;
import apitest.reddit.com.redditapp.utilities.Utils;

/**
 * Created by divyanshunegi on 4/30/15.
 */

public class MainActivity extends ActionBarActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setTitleHeader(this, R.color.reddit_darkest_color);
        setContentView(R.layout.activity_main);

        Intent in = getIntent();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.icon);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle("Reddit");

        ViewPager pager = (ViewPager) findViewById(R.id.vpPagerPlayer);
        pager.setAdapter(new FragmentAdapter(getSupportFragmentManager()));
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setViewPager(pager);
        if(in.getStringExtra("notification")!=null && in.getBooleanExtra("notification", false)){
            pager.setCurrentItem(1);
        }

    }

    @Override
    public void onBackPressed() {
        if (WebviewFragment.webView.canGoBack()) {
            WebviewFragment.webView.goBack();
        } else {
            super.onBackPressed();
        }

    }


}
