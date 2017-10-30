// Developer: Ahmet Kaymak
// Date: 26.10.2017

package com.project.restservice.doctormodule;

import com.project.core.doctormodule.Doctor;
import com.project.core.doctormodule.DoctorRegisterRequest;
import com.project.core.doctormodule.PatientDoctorRate;
import com.project.core.usermodule.User;
import com.project.restservice.FullTextSearchRequest;
import com.project.restservice.serverResponse.ServerResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiDoctor {

    @POST("userModule/doctors/registerDoctor")
    Call<ServerResponse> createDoctor(@Body DoctorRegisterRequest doctorRegisterRequest);

    @GET("userModule/doctors/getDoctorProfile/{user_id}")
    Call<Doctor> getDoctorProfileInformation(@Path("user_id") String userId);

    @POST("userModule/doctors/searchDoctorsFullTextSearch")
    Call<User> searchDoctor(@Body FullTextSearchRequest request);

    @GET("userModule/doctors/getDoctorGeneralRatingParameters/{doctor_id}")
    Call<Doctor> getDoctorGeneralRatingParameters(@Path("doctor_id") String doctorId);

    @POST("doctorModule/doctors/createPatientDoctorRates")
    Call<ServerResponse> createPatientDoctorRates(@Body PatientDoctorRate rate);

    @GET("doctorModule/doctors/getDoctorRankingByDiseaseId/{disease_id}")
    Call<PatientDoctorRate> getDoctorRankingByDiseaseId(@Path("disease_id") String diseaseId);

    @GET("doctorModule/doctors/getPatientDoctorRates/{doctor_id}")
    Call<PatientDoctorRate> getPatientDoctorRates(@Path("doctor_id") String doctorId);
}
