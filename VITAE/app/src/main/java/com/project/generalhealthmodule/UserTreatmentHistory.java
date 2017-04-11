// Developer: Ahmet Kaymak
// Date: 08.04.2017

package com.project.generalhealthmodule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.project.diseasemodule.Disease;
import com.project.treatmentmodule.Treatment;

import java.util.ArrayList;
import java.util.Date;

public class UserTreatmentHistory {

    @SerializedName("user_id")
    @Expose
    private String userId;

    @SerializedName("disease_id")
    @Expose
    private String diseaseId;

    @SerializedName("treatment_id")
    @Expose
    private int treatmentId;

    @SerializedName("treatment_start_date")
    @Expose
    private Date treatmentStartDate;

    @SerializedName("treatment_finish_date")
    @Expose
    private Date treatmentFinishDate;

    @SerializedName("treatment_effect_on_disease")
    @Expose
    private int treatmentEffectOnDisease;

    @SerializedName("created_at")
    @Expose
    private Date created_at;

    @SerializedName("updated_at")
    @Expose
    private Date updated_at;

    @SerializedName("treatment_count")
    @Expose
    private int treatmentCount;

    @SerializedName("TREATMENT")
    @Expose
    private Treatment treatment;

    @SerializedName("DISEASE")
    @Expose
    private Disease disease;

    @SerializedName("user_treatment_history")
    @Expose
    private ArrayList<UserTreatmentHistory> userTreatmentHistory;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(String diseaseId) {
        this.diseaseId = diseaseId;
    }

    public int getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(int treatmentId) {
        this.treatmentId = treatmentId;
    }

    public Date getTreatmentStartDate() {
        return treatmentStartDate;
    }

    public void setTreatmentStartDate(Date treatmentStartDate) {
        this.treatmentStartDate = treatmentStartDate;
    }

    public Date getTreatmentFinishDate() {
        return treatmentFinishDate;
    }

    public void setTreatmentFinishDate(Date treatmentFinishDate) {
        this.treatmentFinishDate = treatmentFinishDate;
    }

    public int getTreatmentEffectOnDisease() {
        return treatmentEffectOnDisease;
    }

    public void setTreatmentEffectOnDisease(int treatmentEffectOnDisease) {
        this.treatmentEffectOnDisease = treatmentEffectOnDisease;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public int getTreatmentCount() {
        return treatmentCount;
    }

    public void setTreatmentCount(int treatmentCount) {
        this.treatmentCount = treatmentCount;
    }

    public Treatment getTreatment() {
        return treatment;
    }

    public void setTreatment(Treatment treatment) {
        this.treatment = treatment;
    }

    public Disease getDisease() {
        return disease;
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
    }

    public ArrayList<UserTreatmentHistory> getUserTreatmentHistory() {
        return userTreatmentHistory;
    }

    public void setUserTreatmentHistory(ArrayList<UserTreatmentHistory> userTreatmentHistory) {
        this.userTreatmentHistory = userTreatmentHistory;
    }
}
