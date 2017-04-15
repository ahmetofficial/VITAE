// Developer: Ahmet Kaymak
// Date: 14.04.2016

package com.project.restservice.hospitalmodule;

import com.project.hospitalmodule.Hospital;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiHospital {

    @POST("hospitalModule/hospitals/searchByHospitalName")
    Call<Hospital> searchByHospitalName(@Body Hospital hospital);
}

