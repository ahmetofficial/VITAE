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
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiUser {

    @POST("userModule/users/login")
    Call<ServerResponse> authenticateLogin(@Body LoginActivity.UserLoginPost userLoginPost);

    @POST("userModule/users/registerPatient")
    Call<ServerResponse> createPatient(@Body Patient patient);

    @GET("userModule/patients/getPatientProfile/{userId}")
    Call<Patient> getPatientProfileInformation(@Path("userId") String userId);

    @POST("userModule/users/searchByUserInfo")
    Call<User> searchUser(@Body FullTextSearchRequest request);

    @POST("userModule/users/areUsersConnected")
    Call<UserRelationship> areUsersConnected(@Body UserRelationship userRelationship);

    @POST("userModule/users/follow")
    Call<ServerResponse> follow(@Body UserRelationship userRelationship);

    @HTTP(method = "DELETE", path = "userModule/users/unfollow", hasBody = true)
    Call<ServerResponse> unfollow(@Body UserRelationship userRelationship);
}
