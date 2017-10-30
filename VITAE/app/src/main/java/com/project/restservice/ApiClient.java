// Developer: Ahmet Kaymak
// Date: 17.02.2016

package com.project.restservice;

import com.project.restservice.diseasemodule.ApiDisease;
import com.project.restservice.doctormodule.ApiDoctor;
import com.project.restservice.drugmodule.ApiDrug;
import com.project.restservice.generalhealthmodule.ApiBlood;
import com.project.restservice.generalhealthmodule.ApiUserDiseaseHistory;
import com.project.restservice.generalhealthmodule.ApiUserDrugUsageHistory;
import com.project.restservice.generalhealthmodule.ApiUserTreatmentHistory;
import com.project.restservice.hospitalmodule.ApiHospital;
import com.project.restservice.imagemodule.ApiImage;
import com.project.restservice.messagemodule.ApiConversation;
import com.project.restservice.messagemodule.ApiMessage;
import com.project.restservice.patientmodule.ApiPatient;
import com.project.restservice.postmodule.ApiPost;
import com.project.restservice.treatmentmodule.ApiTreatment;
import com.project.restservice.usermodule.ApiUser;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String BASE_URL = "http://178.62.223.153:3000/";
    //computer: "http://192.168.1.3:3000/"
    //server:   "http://178.62.223.153:3000/"
    static Retrofit retrofit;
    public static ApiUser userApi;
    public static ApiPost postApi;
    public static ApiBlood bloodApi;
    public static ApiUserDiseaseHistory userDiseaseHistoryApi;
    public static ApiUserDrugUsageHistory userDrugUsageHistoryApi;
    public static ApiUserTreatmentHistory userTreatmentHistoryApi;
    public static ApiTreatment treatmentApi;
    public static ApiHospital hospitalApi;
    public static ApiDisease diseaseApi;
    public static ApiDrug drugApi;
    public static ApiPatient patientApi;
    public static ApiImage imageApi;
    public static ApiMessage messageApi;
    public static ApiConversation conversationApi;
    public static ApiDoctor doctorApi;

    public static ApiUser userApi() {
        retrofit = new Retrofit.Builder()
                .baseUrl( BASE_URL )
                .addConverterFactory( GsonConverterFactory.create() )
                .build();
        userApi = retrofit.create( ApiUser.class );
        return userApi;
    }

    public static ApiPatient patientApi() {
        retrofit = new Retrofit.Builder()
                .baseUrl( BASE_URL )
                .addConverterFactory( GsonConverterFactory.create() )
                .build();
        patientApi = retrofit.create( ApiPatient.class );
        return patientApi;
    }

    public static ApiImage imageApi() {
        retrofit = new Retrofit.Builder()
                .baseUrl( BASE_URL )
                .addConverterFactory( GsonConverterFactory.create() )
                .build();
        imageApi = retrofit.create( ApiImage.class );
        return imageApi;
    }


    public static ApiPost postApi() {
        retrofit = new Retrofit.Builder()
                .baseUrl( BASE_URL )
                .addConverterFactory( GsonConverterFactory.create() )
                .build();
        postApi = retrofit.create( ApiPost.class );
        return postApi;
    }

    public static ApiBlood bloodApi() {
        retrofit = new Retrofit.Builder()
                .baseUrl( BASE_URL )
                .addConverterFactory( GsonConverterFactory.create() )
                .build();
        bloodApi = retrofit.create( ApiBlood.class );
        return bloodApi;
    }

    public static ApiUserDiseaseHistory userDiseaseHistoryApi() {
        retrofit = new Retrofit.Builder()
                .baseUrl( BASE_URL )
                .addConverterFactory( GsonConverterFactory.create() )
                .build();
        userDiseaseHistoryApi = retrofit.create( ApiUserDiseaseHistory.class );
        return userDiseaseHistoryApi;
    }

    public static ApiUserDrugUsageHistory userDrugUsageHistoryApi() {
        retrofit = new Retrofit.Builder()
                .baseUrl( BASE_URL )
                .addConverterFactory( GsonConverterFactory.create() )
                .build();
        userDrugUsageHistoryApi = retrofit.create( ApiUserDrugUsageHistory.class );
        return userDrugUsageHistoryApi;
    }

    public static ApiUserTreatmentHistory userTreatmentHistoryApi() {
        retrofit = new Retrofit.Builder()
                .baseUrl( BASE_URL )
                .addConverterFactory( GsonConverterFactory.create() )
                .build();
        userTreatmentHistoryApi = retrofit.create( ApiUserTreatmentHistory.class );
        return userTreatmentHistoryApi;
    }

    public static ApiHospital hospitalApi() {
        retrofit = new Retrofit.Builder()
                .baseUrl( BASE_URL )
                .addConverterFactory( GsonConverterFactory.create() )
                .build();
        hospitalApi = retrofit.create( ApiHospital.class );
        return hospitalApi;
    }

    public static ApiDisease diseaseApi() {
        retrofit = new Retrofit.Builder()
                .baseUrl( BASE_URL )
                .addConverterFactory( GsonConverterFactory.create() )
                .build();
        diseaseApi = retrofit.create( ApiDisease.class );
        return diseaseApi;
    }

    public static ApiTreatment treatmentApi() {
        retrofit = new Retrofit.Builder()
                .baseUrl( BASE_URL )
                .addConverterFactory( GsonConverterFactory.create() )
                .build();
        treatmentApi = retrofit.create( ApiTreatment.class );
        return treatmentApi;
    }

    public static ApiDrug drugApi() {
        retrofit = new Retrofit.Builder()
                .baseUrl( BASE_URL )
                .addConverterFactory( GsonConverterFactory.create() )
                .build();
        drugApi = retrofit.create( ApiDrug.class );
        return drugApi;
    }

    public static ApiMessage messageApi() {
        retrofit = new Retrofit.Builder()
                .baseUrl( BASE_URL )
                .addConverterFactory( GsonConverterFactory.create() )
                .build();
        messageApi = retrofit.create( ApiMessage.class );
        return messageApi;
    }

    public static ApiConversation conversationApi() {
        retrofit = new Retrofit.Builder()
                .baseUrl( BASE_URL )
                .addConverterFactory( GsonConverterFactory.create() )
                .build();
        conversationApi = retrofit.create( ApiConversation.class );
        return conversationApi;
    }

    public static ApiDoctor doctorApi() {
        retrofit = new Retrofit.Builder()
                .baseUrl( BASE_URL )
                .addConverterFactory( GsonConverterFactory.create() )
                .build();
        doctorApi = retrofit.create( ApiDoctor.class );
        return doctorApi;
    }

}
