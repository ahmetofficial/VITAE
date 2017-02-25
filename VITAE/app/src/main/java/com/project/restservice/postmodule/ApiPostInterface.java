// Developer: Ahmet Kaymak
// Date: 17.02.2016

package com.project.restservice.postmodule;

import com.project.postmodule.UserPost;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiPostInterface {

    @GET("postModule/posts/liveFeed/ahmetkaymak")
    Call<UserPost> getUserPosts();
}
