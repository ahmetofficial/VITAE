// Developer: Ahmet Kaymak
// Date: 17.02.2016

package com.project.restservice.user;

import com.project.usermodule.login.Login;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiUserInterface {
    //@POST("userModule/users/login")
    //Call<LoginResponse> authenticateLogin(@Query("username") String username, @Query("password") String password);
    @POST("userModule/users/login")
    Call<LoginResponse> authenticateLogin(@Body Login.UserLoginPost userLoginPost);
}
