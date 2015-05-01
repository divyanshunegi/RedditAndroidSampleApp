package apitest.reddit.com.redditapp.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import apitest.reddit.com.redditapp.server.api.API;
import apitest.reddit.com.redditapp.utilities.Utils;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by divyanshunegi on 4/30/15.
 */

public class RedditAPIClient {

    private API api;

    public RedditAPIClient() {

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'")
                .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(Utils.API_ROOT)
                .setConverter(new GsonConverter(gson))
                .build();

        api = restAdapter.create(API.class);

    }

    public API getApi()
    {
        return api;
    }

}
