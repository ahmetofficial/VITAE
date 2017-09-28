// Developer: Ahmet Kaymak
// Date: 24.09.2016

package com.project.core.hospitalmodule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;

public class UserHospitalRate {

    @SerializedName("user_id")
    @Expose
    private String userId;

    @SerializedName("hospital_id")
    @Expose
    private int hospitalId;

    @SerializedName("disease_id")
    @Expose
    private String diseaseId;

    @SerializedName("user_rate")
    @Expose
    private double userRate;

    @SerializedName("user_comment")
    @Expose
    private String userComment;

    @SerializedName("created_at")
    @Expose
    private Date createdAt;

    @SerializedName("updated_at")
    @Expose
    private Date updatedAt;

    @SerializedName("rates")
    @Expose
    private ArrayList<UserHospitalRate> userHospitalRates;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(int hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(String diseaseId) {
        this.diseaseId = diseaseId;
    }

    public double getUserRate() {
        return userRate;
    }

    public void setUserRate(double userRate) {
        this.userRate = userRate;
    }

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public ArrayList<UserHospitalRate> getUserHospitalRates() {
        return userHospitalRates;
    }

}
