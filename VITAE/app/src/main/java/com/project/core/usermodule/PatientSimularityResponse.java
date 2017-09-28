// Developer: Ahmet Kaymak
// Date: 21.07.2017

package com.project.core.usermodule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PatientSimularityResponse {

    @SerializedName("user_id")
    @Expose
    private String userId;

    @SerializedName("user_name")
    @Expose
    private String userName;

    @SerializedName("user_type_id")
    @Expose
    private int userTypeId;

    @SerializedName("mail")
    @Expose
    private String mail;

    @SerializedName("similarity_count")
    @Expose
    private int similarityCount;

    @SerializedName("similar_users")
    @Expose
    private ArrayList<PatientSimularityResponse> similarUsers;

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public int getUserTypeId() {
        return userTypeId;
    }

    public String getMail() {
        return mail;
    }

    public int getSimilarityCount() {
        return similarityCount;
    }

    public ArrayList<PatientSimularityResponse> getSimilarUsers() {
        return similarUsers;
    }
}
