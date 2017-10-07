// Developer: Ahmet Kaymak
// Date: 21.07.2017

package com.project.restservice.patientmodule;

import com.project.core.usermodule.Patient;
import com.project.restservice.PatientSimilarityRequest;
import com.project.restservice.PatientSimularityResponse;
import com.project.core.usermodule.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiPatient {

    @POST("userModule/patients/searchSimilarPatient")
    Call<PatientSimularityResponse> searchSimilarUsers(@Body PatientSimilarityRequest patientSimilarityRequest);

    @GET("userModule/patients/getPatientProfile/{user_id}")
    Call<Patient> getPatientProfileInformation(@Path("user_id") String userId);

    @GET("userModule/patients/getFriends/{user_id}")
    Call<User> getFriends(@Path("user_id") String userId);
}
