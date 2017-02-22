// Developer: Ahmet Kaymak
// Date: 17.02.2016

package com.project.restservice.user;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiUserInterface {
    @GET("userModule/users/login")
    Call<LoginResponse> authenticateLogin(@Query("username") String username, @Query("password") String password);

}
