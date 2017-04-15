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

    @GET("postModule/posts/liveFeed/{hospitalName}")
    Call<UserPost> getUserPosts(@Path("hospitalName") String userId);

    @GET("postModule/posts/getByUserId/{hospitalName}")
    Call<UserPost> getUserPostById(@Path("hospitalName") String userId);

    @POST("postModule/posts/create/{hospitalName}")
    Call<ServerResponse> createNewPost(@Path("hospitalName") String userId, @Body UserPost userLoginPost);
}
