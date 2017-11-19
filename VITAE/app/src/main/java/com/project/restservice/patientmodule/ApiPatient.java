// Developer: Ahmet Kaymak
// Date: 21.07.2017

package com.project.restservice.patientmodule;

import com.project.core.patientmodule.Patient;
import com.project.core.usermodule.User;
import com.project.restservice.FullTextSearchRequest;
import com.project.restservice.PatientSimilarityRequest;
import com.project.restservice.serverResponse.PatientSimularityResponse;
import com.project.restservice.serverResponse.ServerResponse;

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

    @GET("userModule/patients/enableBloodAlarms/{user_id}")
    Call<ServerResponse> enableBloodAlarms(@Path("user_id") String userId);

    @GET("userModule/patients/disableBloodAlarms/{user_id}")
    Call<ServerResponse> disableBloodAlarms(@Path("user_id") String userId);

    @GET("userModule/patients/enableSimilarUserSearch/{user_id}")
    Call<ServerResponse> enableSimilarUserSearch(@Path("user_id") String userId);

    @GET("userModule/patients/disableSimilarUserSearch/{user_id}")
    Call<ServerResponse> disableSimilarUserSearch(@Path("user_id") String userId);

    @POST("userModule/patients/registerPatient")
    Call<ServerResponse> createPatient(@Body Patient patient);

    @POST("userModule/patients/searchPatientsFullTextSearch")
    Call<User> searchPatient(@Body FullTextSearchRequest request);
}
