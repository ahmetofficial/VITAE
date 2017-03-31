// Developer: Ahmet Kaymak
// Date: 31.03.2016

package com.project.restservice.generalhealthmodule;

import com.project.generalhealthmodule.UserDiseaseHistory;
import com.project.generalhealthmodule.UserDrugUsageHistory;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiUserDrugUsageHistoryInterface {

    @POST("generalHealthModule/userDiseaseHistory/getHistory/{user_id}")
    Call<UserDiseaseHistory> getUserDiseaseDrugCount(@Path("user_id") String userId, UserDrugUsageHistory userDrugUsageHistory);
}
