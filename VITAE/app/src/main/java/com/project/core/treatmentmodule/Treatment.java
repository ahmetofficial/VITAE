// Developer: Ahmet Kaymak
// Date: 09.04.2017

package com.project.core.treatmentmodule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Treatment {

    @SerializedName("treatment_id")
    @Expose
    private int treatmentId;

    @SerializedName("treatment_name")
    @Expose
    private String treatmentName;

    @SerializedName("avarage_period_in_days")
    @Expose
    private int averagePeriodInDays;

    @SerializedName("body_system_id")
    @Expose
    private int bodySystemId;

    @SerializedName("organ_id")
    @Expose
    private int organId;

    @SerializedName("average_succes_rate")
    @Expose
    private double averageSuccesRate;

    @SerializedName("treatment_list")
    @Expose
    private ArrayList<Treatment> treatments;

    public int getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(int treatmentId) {
        this.treatmentId = treatmentId;
    }

    public String getTreatmentName() {
        return treatmentName;
    }

    public void setTreatmentName(String treatmentName) {
        this.treatmentName = treatmentName;
    }

    public int getAveragePeriodInDays() {
        return averagePeriodInDays;
    }

    public void setAveragePeriodInDays(int averagePeriodInDays) {
        this.averagePeriodInDays = averagePeriodInDays;
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

    public double getAverageSuccesRate() {
        return averageSuccesRate;
    }

    public void setAverageSuccesRate(double averageSuccesRate) {
        this.averageSuccesRate = averageSuccesRate;
    }

    public ArrayList<Treatment> getTreatments() {
        return treatments;
    }
}
