// Developer: Ahmet Kaymak
// Date: 08.04.2017

package com.project.restservice.generalhealthmodule;

import com.project.generalhealthmodule.UserTreatmentHistory;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiUserTreatmentHistory {

    @GET("generalHealthModule/userTreatmentHistory/getHistory/{hospitalName}")
    Call<UserTreatmentHistory> getUserTreatmentHistory(@Path("hospitalName") String userId);
}
