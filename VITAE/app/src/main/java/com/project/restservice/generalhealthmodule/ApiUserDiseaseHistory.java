// Developer: Ahmet Kaymak
// Date: 27.03.2016

package com.project.restservice.generalhealthmodule;

import com.project.core.generalhealthmodule.UserDiseaseHistory;
import com.project.restservice.serverResponse.ServerResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiUserDiseaseHistory {

    @GET("generalHealthModule/userHealthHistory/getHistory/{user_id}")
    Call<UserDiseaseHistory> getUserHealthHistory(@Path("user_id") String userId);

    @GET("generalHealthModule/userDiseaseHistory/getHistory/{user_id}")
    Call<UserDiseaseHistory> getUserDiseaseHistory(@Path("user_id") String userId);

    @POST("generalHealthModule/userDiseaseHistory/create")
    Call<ServerResponse> createUserDiseaseHistory(@Body UserDiseaseHistory userDiseaseHistory);

    @POST("generalHealthModule/userDiseaseHistory/updateTreatmentCount")
    Call<ServerResponse> updateUserDiseaseHistoryTreatmentCount(@Body UserDiseaseHistory userDiseaseHistory);

    @POST("generalHealthModule/userDiseaseHistory/updateDrugCount")
    Call<ServerResponse> updateUserDiseaseHistoryDrugCount(@Body UserDiseaseHistory userDiseaseHistory);

}
