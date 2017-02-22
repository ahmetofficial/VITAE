// Developer: Ahmet Kaymak
// Date: 17.02.2016

package com.project.restservice;

import com.project.restservice.user.ApiUserInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String BASE_URL = "http://178.62.223.153:3000/";
    //private static Retrofit retrofit = null;


    static Retrofit retrofit;
    static ApiUserInterface myApi;

    public static Retrofit getClient() {
        if (retrofit!=null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static ApiUserInterface getMyApi() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myApi = retrofit.create(ApiUserInterface.class);
        return myApi;
    }
}
