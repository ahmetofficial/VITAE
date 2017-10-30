// Developer: Ahmet Kaymak
// Date: 14.04.2016

package com.project.restservice.hospitalmodule;

import com.project.core.hospitalmodule.Clinic;
import com.project.core.hospitalmodule.Hospital;
import com.project.core.hospitalmodule.UserHospitalRate;
import com.project.restservice.serverResponse.ServerResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiHospital {

    @POST("hospitalModule/hospitals/searchByHospitalName")
    Call<Hospital> searchByHospitalName(@Body Hospital hospital);

    @GET("hospitalModule/hospitals/getHospitalById/{hospitalId}")
    Call<Hospital> searchByHospitalId(@Path("hospitalId") int hospitalId);

    @GET("hospitalModule/hospitals/getAllHospitals")
    Call<Hospital> getAllHospitals();

    @GET("hospitalModule/clinics/getAllClinics")
    Call<Clinic> getAllClinics();

    //Hospital Rates

    @POST("hospitalModule/hospitals/createUserHospitalRates")
    Call<ServerResponse> createUserHospitalRate(@Body UserHospitalRate userHospitalRate);

    @GET("hospitalModule/hospitals/getUserHospitalRates/{hospitalId}")
    Call<UserHospitalRate> getHospitalRates(@Path("hospitalId") int hospitalId);

    @GET("hospitalModule/hospitals/getHospitalRankingByDiseaseId/{diseaseId}")
    Call<UserHospitalRate> getHospitalRankingByDiseaseId(@Path("diseaseId") String disease_id);
}

