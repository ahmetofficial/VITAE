// Developer: Ahmet Kaymak
// Date: 08.04.2017

package com.project.restservice.generalhealthmodule;

import com.project.generalhealthmodule.UserTreatmentHistory;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiUserTreatmentHistory {

    @GET("generalHealthModule/userTreatmentHistory/getHistory/{user_id}")
    Call<UserTreatmentHistory> getUserTreatmentHistory(@Path("user_id") String userId);
}
