// Developer: Ahmet Kaymak
// Date: 27.03.2017

package com.project.core.generalhealthmodule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.project.core.diseasemodule.Disease;

import java.util.ArrayList;
import java.util.Date;

public class UserDiseaseHistory {

    @SerializedName("user_id")
    @Expose
    private String userId;

    @SerializedName("disease_id")
    @Expose
    private String diseaseId;

    @SerializedName("disease_start_date")
    @Expose
    private Date diseaseStartDate;

    @SerializedName("disease_finish_date")
    @Expose
    private Date diseaseFinishDate;

    @SerializedName("disease_level_id")
    @Expose
    private int diseaseLevelId;

    @SerializedName("disease_state_id")
    @Expose
    private int diseaseStateId;

    @SerializedName("count_of_treatments")
    @Expose
    private int countOfTreatments;

    @SerializedName("count_of_drugs")
    @Expose
    private int countOfDrugs;

    @SerializedName("created_at")
    @Expose
    private Date created_at;

    @SerializedName("updated_at")
    @Expose
    private Date updated_at;

    @SerializedName("DISEASE")
    @Expose
    private Disease disease;

    @SerializedName("user_disease_history")
    @Expose
    private ArrayList<UserDiseaseHistory> userDiseaseHistories;

    //user health history parameter
    @SerializedName("USER_TREATMENT_HISTORies")
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

    public Date getDiseaseStartDate() {
        return diseaseStartDate;
    }

    public void setDiseaseStartDate(Date diseaseStartDate) {
        this.diseaseStartDate = diseaseStartDate;
    }

    public Date getDiseaseFinishDate() {
        return diseaseFinishDate;
    }

    public void setDiseaseFinishDate(Date diseaseFinishDate) {
        this.diseaseFinishDate = diseaseFinishDate;
    }

    public int getDiseaseLevelId() {
        return diseaseLevelId;
    }

    public void setDiseaseLevelId(int diseaseLevelId) {
        this.diseaseLevelId = diseaseLevelId;
    }

    public int getDiseaseStateId() {
        return diseaseStateId;
    }

    public void setDiseaseStateId(int diseaseStateId) {
        this.diseaseStateId = diseaseStateId;
    }

    public int getCountOfTreatments() {
        return countOfTreatments;
    }

    public int getCountOfDrugs() {
        return countOfDrugs;
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

    public Disease getDisease() {
        return disease;
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
    }

    public ArrayList<UserDiseaseHistory> getUserDiseaseHistories() {
        return userDiseaseHistories;
    }

    public void setUserDiseaseHistories(ArrayList<UserDiseaseHistory> userDiseaseHistories) {
        this.userDiseaseHistories = userDiseaseHistories;
    }

    public ArrayList<UserTreatmentHistory> getUserTreatmentHistory() {
        return userTreatmentHistory;
    }

    public void setUserTreatmentHistory(ArrayList<UserTreatmentHistory> userTreatmentHistory) {
        this.userTreatmentHistory = userTreatmentHistory;
    }
}
