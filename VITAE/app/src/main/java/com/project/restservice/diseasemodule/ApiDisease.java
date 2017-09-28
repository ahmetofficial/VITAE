// Developer: Ahmet Kaymak
// Date: 23.04.2017

package com.project.restservice.diseasemodule;

import com.project.core.diseasemodule.Disease;
import com.project.restservice.FullTextSearchRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiDisease {
    @GET("diseaseModule/diseases/getAll")
    Call<Disease> getAllDisease();

    @POST("diseaseModule/diseases/getDiseaseByName")
    Call<Disease> getDiseaseByName(@Body FullTextSearchRequest fullTextSearchRequest);
}
