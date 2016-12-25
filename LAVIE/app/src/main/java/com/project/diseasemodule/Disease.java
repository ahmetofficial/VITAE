package com.project.diseasemodule;

import com.project.drugmodule.Drug;
import com.project.generalhealthmodule.BodySystem;
import com.project.generalhealthmodule.Organ;
import com.project.hospitalmodule.Branch;
import com.project.hospitalmodule.Clinic;
import com.project.threatmentmodule.Threatment;

import java.util.ArrayList;

/**
 * Created by Ahmet Kaymak on 25.12.2016.
 */

public class Disease {

    public Disease(){};
    public Disease(int diseaseId, String diseaseName, int incubationPeriod, int isChronic, String genderFactor) {
        this.diseaseId = diseaseId;
        this.diseaseName = diseaseName;
        this.incubationPeriod = incubationPeriod;
        this.isChronic = isChronic;
        this.genderFactor = genderFactor;
    }
    public Disease(int diseaseId, String diseaseName, String diseaseLatinName, String diseasePriorReason,
                   int incubationPeriod, int isChronic, String genderFactor, double rateOfAppearance,
                   BodySystem bodySystem, Organ organ) {
        this.diseaseId = diseaseId;
        this.diseaseName = diseaseName;
        this.diseaseLatinName = diseaseLatinName;
        this.diseasePriorReason = diseasePriorReason;
        this.incubationPeriod = incubationPeriod;
        this.isChronic = isChronic;
        this.genderFactor = genderFactor;
        this.rateOfAppearance = rateOfAppearance;
        this.bodySystem = bodySystem;
        this.organ = organ;
    }

    private int diseaseId;
    private String diseaseName;
    private String diseaseLatinName;
    private String diseasePriorReason;
    private int incubationPeriod;
    private int isChronic;
    private String genderFactor;
    private double rateOfAppearance;
    private BodySystem bodySystem;
    private Organ organ;

    private ArrayList<Clinic> diseaseHaveClinics=new ArrayList<>(); //list of clinic which cure that disease
    private ArrayList<Threatment> diseaseHaveThreatments=new ArrayList<>();
    private ArrayList<Drug> diseaseHaveDrugs=new ArrayList<>();
    private ArrayList<Branch> diseaseHaveBranch=new ArrayList<>(); //list of doctor branchs that specialize that disease;

    public int getDiseaseId() {
        return diseaseId;
    }
    public void setDiseaseId(int diseaseId) {
        this.diseaseId = diseaseId;
    }
    public String getDiseaseName() {
        return diseaseName;
    }
    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }
    public String getDiseaseLatinName() {
        return diseaseLatinName;
    }
    public void setDiseaseLatinName(String diseaseLatinName) {
        this.diseaseLatinName = diseaseLatinName;
    }
    public String getDiseaseReason() {
        return diseasePriorReason;
    }
    public void setDiseaseReason(String diseasePriorReason) {
        this.diseasePriorReason = diseasePriorReason;
    }
    public int getIncubationPeriod() {
        return incubationPeriod;
    }
    public void setIncubationPeriod(int incubationPeriod) {
        this.incubationPeriod = incubationPeriod;
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

}
