// Developer: Ahmet Kaymak
// Date: 08.04.2017

package com.project.restservice.generalhealthmodule;

import com.project.generalhealthmodule.UserTreatmentHistory;
import com.project.restservice.ServerResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiUserTreatmentHistory {

    @GET("generalHealthModule/userTreatmentHistory/getHistory/{hospitalName}")
    Call<UserTreatmentHistory> getUserTreatmentHistory(@Path("hospitalName") String userId);

    @POST("generalHealthModule/userTreatmentHistory/create")
    Call<ServerResponse> createUserTreatmentHistory(@Body UserTreatmentHistory userTreatmentHistory);

    @POST("generalHealthModule/userTreatmentHistory/updateDrugCount")
    Call<ServerResponse> updateUserTreatmentHistoryDrugCount(@Body UserTreatmentHistory userTreatmentHistory);
}
