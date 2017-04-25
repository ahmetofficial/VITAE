// Developer: Ahmet Kaymak
// Date: 25.04.2017

package com.project.restservice.drugmodule;

import com.project.drugmodule.Drug;
import com.project.restservice.FullTextSearchRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiDrug {

    @POST("/drugModule/drugs/getDrugByName")
    Call<Drug> getDrugByName(@Body FullTextSearchRequest fullTextSearchRequest);
}
