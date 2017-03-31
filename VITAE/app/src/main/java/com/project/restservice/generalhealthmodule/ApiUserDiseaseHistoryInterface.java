// Developer: Ahmet Kaymak
// Date: 27.03.2016

package com.project.restservice.generalhealthmodule;

import com.project.generalhealthmodule.UserDiseaseHistory;
import com.project.restservice.ServerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiUserDiseaseHistoryInterface {

    @GET("generalHealthModule/userDiseaseHistory/getHistory/{user_id}")
    Call<UserDiseaseHistory> getUserDiseaseHistory(@Path("user_id") String userId);

    @POST("generalHealthModule/userDiseaseHistory/create")
    Call<ServerResponse> createUserDiseaseHistory(UserDiseaseHistory userDiseaseHistory);

}
