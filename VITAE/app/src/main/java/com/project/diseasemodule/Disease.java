// Developer: Ahmet Kaymak
// Date: 25.12.2016


package com.project.diseasemodule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Disease {

    public Disease() {
    }

    public Disease(String diseaseId, String diseaseName, String diseaseReasonId, int incubationPeriodInDays, int isChronic, String genderFactor, double rateOfAppearance, int bodySystemId, int organId) {
        this.diseaseId = diseaseId;
        this.diseaseName = diseaseName;
        this.diseaseReasonId = diseaseReasonId;
        this.incubationPeriodInDays = incubationPeriodInDays;
        this.isChronic = isChronic;
        this.genderFactor = genderFactor;
        this.rateOfAppearance = rateOfAppearance;
        this.bodySystemId = bodySystemId;
        this.organId = organId;
    }

    @SerializedName("disease_id")
    @Expose
    private String diseaseId;

    @SerializedName("disease_name")
    @Expose
    private String diseaseName;

    @SerializedName("disease_reason_id")
    @Expose
    private String diseaseReasonId;

    @SerializedName("incubation_period_in_days")
    @Expose
    private int incubationPeriodInDays;

    @SerializedName("is_chronic")
    @Expose
    private int isChronic;

    @SerializedName("gender_factor")
    @Expose
    private String genderFactor;

    @SerializedName("rate_of_appearance")
    @Expose
    private double rateOfAppearance;

    @SerializedName("body_system_id")
    @Expose
    private int bodySystemId;

    @SerializedName("organ_id")
    @Expose
    private int organId;

    public String getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(String diseaseId) {
        this.diseaseId = diseaseId;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public String getDiseaseReasonId() {
        return diseaseReasonId;
    }

    public void setDiseaseReasonId(String diseaseReasonId) {
        this.diseaseReasonId = diseaseReasonId;
    }

    public int getIncubationPeriodInDays() {
        return incubationPeriodInDays;
    }

    public void setIncubationPeriodInDays(int incubationPeriodInDays) {
        this.incubationPeriodInDays = incubationPeriodInDays;
    }

    public int getIsChronic() {
        return isChronic;
    }

    public void setIsChronic(int isChronic) {
        this.isChronic = isChronic;
    }

    public String getGenderFactor() {
        return genderFactor;
    }

    public void setGenderFactor(String genderFactor) {
        this.genderFactor = genderFactor;
    }

    public double getRateOfAppearance() {
        return rateOfAppearance;
    }

    public void setRateOfAppearance(double rateOfAppearance) {
        this.rateOfAppearance = rateOfAppearance;
    }

    public int getBodySystemId() {
        return bodySystemId;
    }

    public void setBodySystemId(int bodySystemId) {
        this.bodySystemId = bodySystemId;
    }

    public int getOrganId() {
        return organId;
    }

    public void setOrganId(int organId) {
        this.organId = organId;
    }
}
