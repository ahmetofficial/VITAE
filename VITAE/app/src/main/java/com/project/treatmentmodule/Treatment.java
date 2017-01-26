package com.project.treatmentmodule;

import com.project.diseasemodule.Disease;
import com.project.drugmodule.Drug;
import com.project.generalhealthmodule.BodySystem;
import com.project.generalhealthmodule.Organ;

import java.util.ArrayList;

/**
 * Created by Ahmet Kaymak on 25.12.2016.
 */

public class Treatment {

    public Treatment(){}
    public Treatment(int threatmentId, String threatmentName) {
        this.threatmentId = threatmentId;
        this.threatmentName = threatmentName;
    }
    public Treatment(int threatmentId, String threatmentName, BodySystem bodySystem, Organ organ) {
        this.threatmentId = threatmentId;
        this.threatmentName = threatmentName;
        this.bodySystem = bodySystem;
        this.organ = organ;
    }
    public Treatment(int threatmentId, String threatmentName, String ageRange, int averagePeriodInDays,
                     BodySystem bodySystem, Organ organ, double averageSuccessRate) {
        this.threatmentId = threatmentId;
        this.threatmentName = threatmentName;
        this.ageRange = ageRange;
        this.averagePeriodInDays = averagePeriodInDays;
        this.bodySystem = bodySystem;
        this.organ = organ;
        this.averageSuccessRate = averageSuccessRate;
    }


    private int threatmentId;
    private String threatmentName;
    private String ageRange; // will be added such "X-Y"
    private int averagePeriodInDays;
    private BodySystem bodySystem;
    private Organ organ;
    private double averageSuccessRate;

    private ArrayList<Disease> threatmentHaveDiseases=new ArrayList<>(); //the list of diseases that threatment is used.
    private ArrayList<Drug> threatmentHaveDrugs=new ArrayList<>(); //the list of drugs that threatment is used.

    public int getThreatmentId() {
        return threatmentId;
    }
    public void setThreatmentId(int threatmentId) {
        this.threatmentId = threatmentId;
    }
    public String getThreatmentName() {
        return threatmentName;
    }
    public void setThreatmentName(String threatmentName) {
        this.threatmentName = threatmentName;
    }
    public String getAgeRange() {
        return ageRange;
    }
    public void setAgeRange(String ageRange) {
        this.ageRange = ageRange;
    }
    public int getAveragePeriodInDays() {
        return averagePeriodInDays;
    }
    public void setAveragePeriodInDays(int averagePeriodInDays) {
        this.averagePeriodInDays = averagePeriodInDays;
    }
    public BodySystem getBodySystem() {
        return bodySystem;
    }
    public void setBodySystem(BodySystem bodySystem) {
        this.bodySystem = bodySystem;
    }
    public Organ getOrgan() {
        return organ;
    }
    public void setOrgan(Organ organ) {
        this.organ = organ;
    }
    public double getAverageSuccessRate() {
        return averageSuccessRate;
    }
    public void setAverageSuccessRate(double averageSuccessRate) {
        this.averageSuccessRate = averageSuccessRate;
    }

}
