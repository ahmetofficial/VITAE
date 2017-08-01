// Developer: Ahmet Kaymak
// Date: 17.02.2016

package com.project.restservice.usermodule;

import com.project.restservice.FullTextSearchRequest;
import com.project.restservice.ServerResponse;
import com.project.uimodule.login.LoginActivity;
import com.project.usermodule.Patient;
import com.project.usermodule.User;
import com.project.usermodule.UserRelationship;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiUser {

    @POST("userModule/users/login")
    Call<ServerResponse> authenticateLogin(@Body LoginActivity.UserLoginPost userLoginPost);

    @POST("userModule/users/registerPatient")
    Call<ServerResponse> createPatient(@Body Patient patient);

    @GET("userModule/users/getUserInformation/{userId}")
    Call<User> getUserInformation(@Path("userId") String userId);

    @GET("userModule/users/isUserIdAvaible/{userId}")
    Call<ServerResponse> isUserIdAvaible(@Path("userId") String userId);

    @POST("userModule/users/searchByUserInfo")
    Call<User> searchUser(@Body FullTextSearchRequest request);

    @POST("userModule/users/areUsersConnected")
    Call<UserRelationship> areUsersConnected(@Body UserRelationship userRelationship);

    @POST("userModule/users/follow")
    Call<ServerResponse> follow(@Body UserRelationship userRelationship);

    @HTTP(method = "DELETE", path = "userModule/users/unfollow", hasBody = true)
    Call<ServerResponse> unfollow(@Body UserRelationship userRelationship);

    @PUT("userModule/users/resetUserName/{userId}")
    Call<ServerResponse> resetUserName(@Path("userId") String userId, @Body User user);

    @PUT("userModule/users/resetPassword/{userId}")
    Call<ServerResponse> resetPassword(@Path("userId") String userId, @Body User user);

    @PUT("userModule/users/resetAboutMe/{userId}")
    Call<ServerResponse> resetAboutMe(@Path("userId") String userId, @Body User user);

    @PUT("userModule/users/resetMail/{userId}")
    Call<ServerResponse> resetMail(@Path("userId") String userId, @Body User user);
}
