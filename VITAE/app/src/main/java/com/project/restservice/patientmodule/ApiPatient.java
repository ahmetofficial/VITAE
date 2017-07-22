// Developer: Ahmet Kaymak
// Date: 21.07.2017

package com.project.restservice.patientmodule;

import com.project.usermodule.PatientSimilarityRequest;
import com.project.usermodule.PatientSimularityResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiPatient {

    @POST("userModule/patients/searchSimilarPatient")
    Call<PatientSimularityResponse> areUsersConnected(@Body PatientSimilarityRequest patientSimilarityRequest);
}
