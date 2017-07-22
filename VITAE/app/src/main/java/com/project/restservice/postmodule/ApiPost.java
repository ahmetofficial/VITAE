// Developer: Ahmet Kaymak
// Date: 17.02.2016

package com.project.restservice.postmodule;

import com.project.postmodule.UserPost;
import com.project.restservice.ServerResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiPost {

    @GET("postModule/posts/liveFeed/{user_id}")
    Call<UserPost> getUserTimeline(@Path("user_id") String userId);

    @GET("postModule/posts/getByUserId/{user_id}")
    Call<UserPost> getUserPostById(@Path("user_id") String userId);

    @POST("postModule/posts/create/{user_id}")
    Call<ServerResponse> createNewPost(@Path("user_id") String userId, @Body UserPost userLoginPost);
}
