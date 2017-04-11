// Developer: Ahmet Kaymak
// Date: 17.02.2016

package com.project.restservice;

import com.project.restservice.generalhealthmodule.ApiBlood;
import com.project.restservice.generalhealthmodule.ApiUserDiseaseHistory;
import com.project.restservice.generalhealthmodule.ApiUserDrugUsageHistory;
import com.project.restservice.generalhealthmodule.ApiUserTreatmentHistory;
import com.project.restservice.postmodule.ApiPost;
import com.project.restservice.usermodule.ApiUser;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String BASE_URL = "http://178.62.223.153:3000/";
    static Retrofit retrofit;
    static ApiUser userApi;
    static ApiPost postApi;
    static ApiBlood bloodApi;
    static ApiUserDiseaseHistory userDiseaseHistoryApi;
    static ApiUserDrugUsageHistory userDrugUsageHistoryApi;
    static ApiUserTreatmentHistory userTreatmentHistoryApi;

    public static ApiUser userApi() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        userApi = retrofit.create(ApiUser.class);
        return userApi;
    }

    public static ApiPost postApi() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        postApi = retrofit.create(ApiPost.class);
        return postApi;
    }

    public static ApiBlood bloodApi() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        bloodApi = retrofit.create(ApiBlood.class);
        return bloodApi;
    }

    public static ApiUserDiseaseHistory userDiseaseHistoryApi(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        userDiseaseHistoryApi = retrofit.create( ApiUserDiseaseHistory.class );
        return  userDiseaseHistoryApi;
    }

    public static ApiUserDrugUsageHistory userDrugUsageHistoryApi(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        userDrugUsageHistoryApi = retrofit.create( ApiUserDrugUsageHistory.class );
        return  userDrugUsageHistoryApi;
    }

    public static ApiUserTreatmentHistory userTreatmentHistoryApi(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        userTreatmentHistoryApi = retrofit.create( ApiUserTreatmentHistory.class );
        return  userTreatmentHistoryApi;
    }

}
