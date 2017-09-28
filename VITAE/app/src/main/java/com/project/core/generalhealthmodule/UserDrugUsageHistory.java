// Developer: Ahmet Kaymak
// Date: 31.03.2017

package com.project.core.generalhealthmodule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.project.core.diseasemodule.Disease;
import com.project.core.drugmodule.Drug;
import com.project.core.treatmentmodule.Treatment;

import java.util.ArrayList;
import java.util.Date;

public class UserDrugUsageHistory {

    @SerializedName("user_id")
    @Expose
    private String userId;

    @SerializedName("disease_id")
    @Expose
    private String diseaseId;

    @SerializedName("treatment_id")
    @Expose
    private int treatmentId;

    @SerializedName("drug_id")
    @Expose
    private int drugId;

    @SerializedName("drug_usage_start_date")
    @Expose
    private Date drugUsageStartDate;

    @SerializedName("drug_usage_finish_date")
    @Expose
    private Date drugUsageFinishDate;

    @SerializedName("drug_effect_on_disease")
    @Expose
    private int drugEffectOnDisease;

    @SerializedName("created_at")
    @Expose
    private Date created_at;

    @SerializedName("updated_at")
    @Expose
    private Date updated_at;

    @SerializedName("drug_count")
    @Expose
    private int drugCount;

    @SerializedName("TREATMENT")
    @Expose
    private Treatment treatment;

    @SerializedName("DISEASE")
    @Expose
    private Disease disease;

    @SerializedName("DRUG")
    @Expose
    private Drug drug;

    @SerializedName("user_drug_usage_history")
    @Expose
    private ArrayList<UserDrugUsageHistory> userDrugUsageHistory;

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

    public int getDrugId() {
        return drugId;
    }

    public void setDrugId(int drugId) {
        this.drugId = drugId;
    }

    public Date getDrugUsageStartDate() {
        return drugUsageStartDate;
    }

    public void setDrugUsageStartDate(Date drugUsageStartDate) {
        this.drugUsageStartDate = drugUsageStartDate;
    }

    public Date getDrugUsageFinishDate() {
        return drugUsageFinishDate;
    }

    public void setDrugUsageFinishDate(Date drugUsageFinishDate) {
        this.drugUsageFinishDate = drugUsageFinishDate;
    }

    public int getDrugEffectOnDisease() {
        return drugEffectOnDisease;
    }

    public void setDrugEffectOnDisease(int drugEffectOnDisease) {
        this.drugEffectOnDisease = drugEffectOnDisease;
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

    public int getDrugCount() {
        return drugCount;
    }

    public void setDrugCount(int drugCount) {
        this.drugCount = drugCount;
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

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    public ArrayList<UserDrugUsageHistory> getUserDrugUsageHistory() {
        return userDrugUsageHistory;
    }

    public void setUserDrugUsageHistory(ArrayList<UserDrugUsageHistory> userDrugUsageHistory) {
        this.userDrugUsageHistory = userDrugUsageHistory;
    }
}
