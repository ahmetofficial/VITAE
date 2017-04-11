// Developer: Ahmet Kaymak
// Date: 31.03.2017

package com.project.restservice.generalhealthmodule;

import com.project.generalhealthmodule.UserDrugUsageHistory;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiUserDrugUsageHistory {

    @GET("generalHealthModule/userDrugUsageHistory/getHistory/{user_id}")
    Call<UserDrugUsageHistory> getUserDrugUsageHistory(@Path("user_id") String userId);

}
