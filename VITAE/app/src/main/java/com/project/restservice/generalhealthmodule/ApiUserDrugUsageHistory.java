// Developer: Ahmet Kaymak
// Date: 31.03.2017

package com.project.restservice.generalhealthmodule;

import com.project.generalhealthmodule.UserDrugUsageHistory;
import com.project.restservice.ServerResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiUserDrugUsageHistory {

    @GET("generalHealthModule/userDrugUsageHistory/getHistory/{user_id}")
    Call<UserDrugUsageHistory> getUserDrugUsageHistory(@Path("user_id") String userId);

    @POST("generalHealthModule/userDrugUsageHistory/create")
    Call<ServerResponse> createUserDrugUsageHistory(@Body UserDrugUsageHistory userDrugUsageHistory);

    @GET("generalHealthModule/userDrugUsageHistory/getTreatmentDrugUsageHistory/{user_id}/{disease_id}/{treatment_id}")
    Call<UserDrugUsageHistory> getTreatmentDrugUsageHistory(@Path("user_id") String userId, @Path("disease_id") String diseaseId, @Path("treatment_id") int treatmentId);
}
