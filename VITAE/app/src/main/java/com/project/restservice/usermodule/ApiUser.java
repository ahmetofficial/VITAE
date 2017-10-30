// Developer: Ahmet Kaymak
// Date: 17.02.2016

package com.project.restservice.usermodule;

import com.project.core.patientmodule.PatientForConversation;
import com.project.core.usermodule.User;
import com.project.core.usermodule.UserLocation;
import com.project.core.usermodule.UserRelationship;
import com.project.restservice.serverResponse.ServerResponse;
import com.project.ui.login.LoginActivity;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiUser {

    //USERS
    @POST("userModule/users/login")
    Call<ServerResponse> authenticateLogin(@Body LoginActivity.UserLoginPost userLoginPost);

    @GET("userModule/users/getUserInformation/{userId}")
    Call<User> getUserInformation(@Path("userId") String userId);

    @GET("userModule/users/isUserIdAvaible/{userId}")
    Call<ServerResponse> isUserIdAvaible(@Path("userId") String userId);

    @POST("userModule/users/areUsersConnected")
    Call<UserRelationship> areUsersConnected(@Body UserRelationship userRelationship);

    @POST("userModule/users/follow")
    Call<ServerResponse> follow(@Body UserRelationship userRelationship);

    @POST("updateUserDeviceToken")
    Call<ServerResponse> updateUserDeviceToken(@Body User user);

    @POST("userModule/users/saveLocation")
    Call<ServerResponse> saveUserLocation(@Body UserLocation userLocation);

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

    @GET("userModule/users/getFriendsWithConversationStatus/{user_id}")
    Call<PatientForConversation> getFriendsWithConversationStatus(@Path("user_id") String userId);

    @GET("userModule/users/getFriends/{user_id}")
    Call<User> getUserFriends(@Path("user_id") String userId);
}
