// Developer: Ahmet Kaymak
// Date: 17.02.2016

package com.project.restservice.usermodule;

import com.project.uimodule.login.LoginActivity;
import com.project.usermodule.Patient;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiUserInterface {

    @POST("userModule/users/login")
    Call<SignInUpResponse> authenticateLogin(@Body LoginActivity.UserLoginPost userLoginPost);

    @POST("userModule/users/registerPatient")
    Call<SignInUpResponse> createPatient(@Body Patient patient);
}
