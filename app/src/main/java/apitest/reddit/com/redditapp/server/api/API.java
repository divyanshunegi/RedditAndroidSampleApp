package apitest.reddit.com.redditapp.server.api;

import apitest.reddit.com.redditapp.server.model.HotPostBean;
import retrofit.Callback;
import retrofit.http.GET;

public interface API {

  @GET("/hot.json")
  void getHotPost(
          Callback<HotPostBean> callback);
}