// Developer: Ahmet Kaymak
// Date: 25.04.2017

package com.project.restservice.treatmentmodule;

import com.project.restservice.FullTextSearchRequest;
import com.project.treatmentmodule.Treatment;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiTreatment {

    @POST("/treatmentModule/treatments/getTreatmentByName")
    Call<Treatment> getTreatmentByName(@Body FullTextSearchRequest fullTextSearchRequest);
}
