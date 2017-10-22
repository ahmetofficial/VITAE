// Developer: Ahmet Kaymak
// Date: 17.02.2016

package com.project.restservice.postmodule;

import com.project.core.postmodule.UserPost;
import com.project.core.postmodule.UserPostLike;
import com.project.restservice.serverresponse.ServerResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiPost {

    @GET("postModule/posts/liveFeed/{user_id}")
    Call<UserPost> getUserTimeline(@Path("user_id") String userId);

    @GET("postModule/posts/getByUserId/{user_id}")
    Call<UserPost> getUserPostsById(@Path("user_id") String userId);

    @POST("postModule/posts/createPost/{user_id}")
    Call<ServerResponse> createNewPost(@Path("user_id") String userId, @Body UserPost userLoginPost);

    @POST("postModule/posts/createPost/{user_id}/{photo_id}")
    Call<ServerResponse> createNewPostWithPhoto(@Path("user_id") String userId,@Path("photo_id") String photoId, @Body UserPost userLoginPost);

    ////////////////////////////////////POST LIKE///////////////////////////////////////////////////

    @POST("postModule/posts/createPostLike")
    Call<ServerResponse> like(@Body UserPostLike userPostLike);

    @POST("postModule/posts/deletePostLike")
    Call<ServerResponse> unlike(@Body UserPostLike userPostLike);
}
