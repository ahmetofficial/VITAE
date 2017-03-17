// Developer: Ahmet Kaymak
// Date: 17.02.2016

package com.project.restservice.postmodule;

import com.project.postmodule.UserPost;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiPostInterface {

    @GET("postModule/posts/liveFeed/{user_id}")
    Call<UserPost> getUserPosts(@Path("user_id") String userId);

    @GET("postModule/posts/getByUserId/{user_id}")
    Call<UserPost> getUserPostById(@Path("user_id") String userId);

}
