// Developer: Ahmet Kaymak
// Date: 17.02.2016

package com.project.restservice;

import com.project.restservice.generalhealthmodule.ApiBloodInterface;
import com.project.restservice.generalhealthmodule.ApiUserDiseaseHistoryInterface;
import com.project.restservice.generalhealthmodule.ApiUserDrugUsageHistoryInterface;
import com.project.restservice.postmodule.ApiPostInterface;
import com.project.restservice.usermodule.ApiUserInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String BASE_URL = "http://178.62.223.153:3000/";
    static Retrofit retrofit;
    static ApiUserInterface userApi;
    static ApiPostInterface postApi;
    static ApiBloodInterface bloodApi;
    static ApiUserDiseaseHistoryInterface userDiseaseHistoryApi;
    static ApiUserDrugUsageHistoryInterface userDrugUsageHistoryApi;

    public static ApiUserInterface userApi() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        userApi = retrofit.create(ApiUserInterface.class);
        return userApi;
    }

    public static ApiPostInterface postApi() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        postApi = retrofit.create(ApiPostInterface.class);
        return postApi;
    }

    public static ApiBloodInterface bloodApi() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        bloodApi = retrofit.create(ApiBloodInterface.class);
        return bloodApi;
    }

    public static ApiUserDiseaseHistoryInterface userDiseaseHistoryApi(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        userDiseaseHistoryApi = retrofit.create( ApiUserDiseaseHistoryInterface.class );
        return  userDiseaseHistoryApi;
    }

    public static ApiUserDrugUsageHistoryInterface userDrugUsageHistoryApi(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        userDrugUsageHistoryApi = retrofit.create( ApiUserDrugUsageHistoryInterface.class );
        return  userDrugUsageHistoryApi;
    }

}
