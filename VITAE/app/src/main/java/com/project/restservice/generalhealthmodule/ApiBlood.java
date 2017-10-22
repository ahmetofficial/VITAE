// Developer: Ahmet Kaymak
// Date: 10.03.2016

package com.project.restservice.generalhealthmodule;

import com.project.core.generalhealthmodule.BloodAlarm;
import com.project.core.generalhealthmodule.BloodType;
import com.project.restservice.serverresponse.ServerResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiBlood {

    @POST("generalHealthModule/bloodTypes/getBloodType'")
    Call<BloodType> getBloodType(@Body BloodType bloodType);

    @POST("generalHealthModule/bloodTypes/getBloodId")
    Call<BloodType> getBloodId(@Body BloodType bloodType);

    @POST("generalHealthModule/bloodAlarm/create")
    Call<ServerResponse> createBloodAlert(@Body BloodAlarm bloodAlarm);

    @POST("generalHealthModule/bloodAlarm/updateBloodAlarmLevel")
    Call<ServerResponse> updateBloodAlarmLevel(@Body BloodAlarm bloodAlarm);

    @POST("generalHealthModule/bloodAlarm/finishBloodAlarm")
    Call<ServerResponse> finishBloodAlarm(@Body BloodAlarm bloodAlarm);

    @GET("generalHealthModule/bloodAlarm/getAllBloodAlarms")
    Call<BloodAlarm> getAllBloodAlarms();

    @GET("generalHealthModule/bloodAlarm/getBloodAlarmsByBloodType/{blood_type_id}")
    Call<BloodAlarm> getBloodAlarmsByBloodType(@Path("blood_type_id") int blood_type_id);
}
