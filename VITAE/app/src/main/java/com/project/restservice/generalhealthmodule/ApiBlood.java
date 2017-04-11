// Developer: Ahmet Kaymak
// Date: 10.03.2016

package com.project.restservice.generalhealthmodule;

import com.project.generalhealthmodule.BloodType;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiBlood {
    @POST("generalHealthModule/bloodTypes/getBloodType'")
    Call<BloodType> getBloodType(@Body BloodType bloodType);

    @POST("generalHealthModule/bloodTypes/getBloodId")
    Call<BloodType> getBloodId(@Body BloodType bloodType);
}
