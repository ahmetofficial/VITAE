// Developer: Ahmet Kaymak
// Date: 08.04.2017

package com.project.restservice.generalhealthmodule;

import com.project.core.generalhealthmodule.UserTreatmentHistory;
import com.project.restservice.serverresponse.ServerResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiUserTreatmentHistory {

    @GET("generalHealthModule/userTreatmentHistory/getHistory/{user_id}")
    Call<UserTreatmentHistory> getUserTreatmentHistory(@Path("user_id") String userId);

    @GET("generalHealthModule/userTreatmentHistory/getDiseaseTreatmentHistory/{user_id}/{disease_id}")
    Call<UserTreatmentHistory> getDiseaseTreatmentHistory(@Path("user_id") String userId,@Path("disease_id") String disease_id);

    @POST("generalHealthModule/userTreatmentHistory/create")
    Call<ServerResponse> createUserTreatmentHistory(@Body UserTreatmentHistory userTreatmentHistory);

    @POST("generalHealthModule/userTreatmentHistory/updateDrugCount")
    Call<ServerResponse> updateUserTreatmentHistoryDrugCount(@Body UserTreatmentHistory userTreatmentHistory);
}
