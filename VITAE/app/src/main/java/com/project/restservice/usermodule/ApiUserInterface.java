// Developer: Ahmet Kaymak
// Date: 17.02.2016

package com.project.restservice.usermodule;

import com.project.usermodule.login.LoginActivity;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiUserInterface {

    @POST("userModule/users/login")
    Call<LoginResponse> authenticateLogin(@Body LoginActivity.UserLoginPost userLoginPost);
}
