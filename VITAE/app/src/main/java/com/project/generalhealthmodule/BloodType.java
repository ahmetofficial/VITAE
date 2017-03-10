// Developer: Ahmet Kaymak
// Date: 10.03.2016

package com.project.generalhealthmodule;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.project.restservice.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BloodType {

    public BloodType(int bloodTypeId) {
        this.bloodTypeId=bloodTypeId;
    }

    public BloodType(String bloodType, String rhFactor){
        this.bloodType=bloodType;
        this.rhFactor=rhFactor;
    }

    @SerializedName("blood_type_id")
    @Expose
    private int bloodTypeId;

    @SerializedName("blood_type")
    @Expose
    private String bloodType;

    @SerializedName("rh_factor")
    @Expose
    private String rhFactor;

    public static int getBloodTypeId(BloodType bloodType){
        final int[] bloodTypeId = {0};
        ApiClient.bloodApi().getBloodId(bloodType)
                .enqueue(new Callback<BloodType>() {
                    @Override
                    public void onResponse(Call<BloodType> call, Response<BloodType> response) {
                        if (response.isSuccessful()) {
                            if (response.body().getBloodType()!=null){
                                bloodTypeId[0] =response.body().getBloodTypeId();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<BloodType> call, Throwable t) {
                        Log.e("MenuActivity", t.getMessage());
                    }
                });
        return bloodTypeId[0];
    }

    public static String getBloodTypeName(BloodType bloodType){
        final String[] bloodTypeName = new String[1];
        ApiClient.bloodApi().getBloodId(bloodType)
                .enqueue(new Callback<BloodType>() {
                    @Override
                    public void onResponse(Call<BloodType> call, Response<BloodType> response) {
                        if (response.isSuccessful()) {
                            if (response.body().getBloodType()!=null){
                                bloodTypeName[0] =response.body().getBloodType();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<BloodType> call, Throwable t) {
                        Log.e("MenuActivity", t.getMessage());
                    }
                });
        return bloodTypeName[0];
    }

    public int getBloodTypeId() {
        return bloodTypeId;
    }

    public void setBloodTypeId(int bloodTypeId) {
        this.bloodTypeId = bloodTypeId;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getRhFactor() {
        return rhFactor;
    }

    public void setRhFactor(String rhFactor) {
        this.rhFactor = rhFactor;
    }

}