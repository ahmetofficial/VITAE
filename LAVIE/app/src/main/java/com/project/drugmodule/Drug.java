package com.project.drugmodule;

import com.project.diseasemodule.Disease;
import com.project.threatmentmodule.Threatment;

import java.security.PublicKey;
import java.util.ArrayList;

/**
 * Created by Ahmet Kaymak on 25.12.2016.
 */

public class Drug {

    public Drug(){};
    public Drug(int drugId, String commercialName) {
        this.drugId = drugId;
        this.commercialName = commercialName;
    }

    //Class Fields
    private int drugId;
    private String commercialName;
    private String drugType;
    private String chemicalName;
    private String formOfDrug;
    private String ageRange; // will be used in "X-Y" format
    private int absortionTimeInMinutes;
    private String generalDescription;
    private String prescriptionType;

    private ArrayList<Disease> drugHaveDiseases=new ArrayList<>(); //the list of diseases that drug is used.
    private ArrayList<Threatment> drugHaveThreatments=new ArrayList<>(); //the list of threatments that drug is used.

    public int getDrugId() {
        return drugId;
    }
    public void setDrugId(int drugId) {
        this.drugId = drugId;
    }
    public String getCommercialName() {
        return commercialName;
    }
    public void setCommercialName(String commercialName) {
        this.commercialName = commercialName;
    }
    public String getDrugType() {
        return drugType;
    }
    public void setDrugType(String drugType) {
        this.drugType = drugType;
    }
    public String getChemicalName() {
        return chemicalName;
    }
    public void setChemicalName(String chemicalName) {
        this.chemicalName = chemicalName;
    }
    public String getFormOfDrug() {
        return formOfDrug;
    }
    public void setFormOfDrug(String formOfDrug) {
        this.formOfDrug = formOfDrug;
    }
    public String getAgeRange() {
        return ageRange;
    }
    public void setAgeRange(String ageRange) {
        this.ageRange = ageRange;
    }
    public String getGeneralDescription() {
        return generalDescription;
    }
    public void setGeneralDescription(String generalDescription) {
        this.generalDescription = generalDescription;
    }
    public int getAbsortionTimeInMinutes() {
        return absortionTimeInMinutes;
    }
    public void setAbsortionTimeInMinutes(int absortionTimeInMinutes) {
        this.absortionTimeInMinutes = absortionTimeInMinutes;
    }
    public String getPrescriptionType() {
        return prescriptionType;
    }
    public void setPrescriptionType(String prescriptionType) {
        this.prescriptionType = prescriptionType;
    }

}
