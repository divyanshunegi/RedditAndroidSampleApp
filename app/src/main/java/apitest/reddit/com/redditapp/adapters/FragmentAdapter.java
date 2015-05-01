package apitest.reddit.com.redditapp.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import apitest.reddit.com.redditapp.fragment.ApiFragment;
import apitest.reddit.com.redditapp.fragment.WebviewFragment;


/**
 * Created by divyanshunegi on 4/30/15.
 */

public class FragmentAdapter extends FragmentPagerAdapter {

    // Tab Titles
    private String tabTitles[] = new String[]{"reddit", "Notification Posts"};
    private final int PAGE_COUNT = 2;

    public FragmentAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {

            case 0:
                return WebviewFragment.newInstance();
            case 1:
                return ApiFragment.newInstance();

        }
        return null;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

}
