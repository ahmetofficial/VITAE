// Developer: Ahmet Kaymak
// Date: 10.03.2016

package com.project.usermodule;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.lavie.users.R;
import com.project.restservice.ApiClient;
import com.project.restservice.usermodule.SignInUpResponse;
import com.project.uimodule.main.MenuActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Patient extends User {

    @SerializedName("gender")
    @Expose
    private String gender;

    @SerializedName("blood_type_id")
    @Expose
    private int bloodTypeId;

    @SerializedName("birthday")
    @Expose
    private String birthday;

    @SerializedName("PATIENTs")
    @Expose
    private ArrayList<Patient> thisPatient;

    public static void createPatient(Patient patient, final Fragment fragment) {
        ApiClient.userApi().createPatient(patient).enqueue(new Callback<SignInUpResponse>() {
            @Override
            public void onResponse(Call<SignInUpResponse> call, Response<SignInUpResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equals("true")) {
                        Toast.makeText(fragment.getActivity(), fragment.getContext().getString(R.string.user_succeffully_created), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(fragment.getActivity(), MenuActivity.class);
                        fragment.startActivity(intent);
                    }else{
                        Toast.makeText(fragment.getActivity(), fragment.getContext().getString(R.string.user_cannot_created), Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<SignInUpResponse> call, Throwable t) {
                Log.e("MenuActivity", t.getMessage());
            }
        });
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getBloodTypeId() {
        return bloodTypeId;
    }

    public void setBloodTypeId(int bloodTypeId) {
        this.bloodTypeId = bloodTypeId;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public ArrayList<Patient> getThisPatient() {
        return thisPatient;
    }

}
