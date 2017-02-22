package com.project.drugmodule;

import com.project.diseasemodule.Disease;
import com.project.treatmentmodule.Treatment;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

// Developer: Ahmet Kaymak
// Date: 25.12.2016

public class Drug {

    public Drug(){};
    public Drug(int drugId, String commercialName) {
        this.drugId = drugId;
        this.commercialName = commercialName;
    }

    //Class Fields
    @SerializedName("drug_id")
    private int drugId;
    @SerializedName("commercial_name")
    private String commercialName;
    @SerializedName("chemical_name")
    private String chemicalName;
    @SerializedName("type_id")
    private String drugTypeId;
    @SerializedName("prescription_type_id")
    private int prescriptionTypeId;
    @SerializedName("form_id")
    private int drugFormId;
    @SerializedName("company_id")
    private int drugCompanyId;
    @SerializedName("general_descriptions")
    private String generalDescription;

    private DrugType drugType=new DrugType(drugTypeId);
    private DrugForm drugForm=new DrugForm(drugFormId);
    private DrugCompany drugCompany=new DrugCompany(drugCompanyId);
    private DrugPresciription drugPresciription=new DrugPresciription(prescriptionTypeId);
    private ArrayList<Disease> drugHaveDiseases=new ArrayList<>(); //the list of diseases that drug is used.
    private ArrayList<Treatment> drugHaveTreatments =new ArrayList<>(); //the list of threatments that drug is used.

    //setter-getter methods
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
    public String getChemicalName() {
        return chemicalName;
    }
    public void setChemicalName(String chemicalName) {
        this.chemicalName = chemicalName;
    }
    public String getDrugTypeId() {
        return drugTypeId;
    }
    public void setDrugTypeId(String drugTypeId) {
        this.drugTypeId = drugTypeId;
    }
    public int getPrescriptionTypeId() {
        return prescriptionTypeId;
    }
    public void setPrescriptionTypeId(int prescriptionTypeId) {
        this.prescriptionTypeId = prescriptionTypeId;
    }
    public int getDrugFormId() {
        return drugFormId;
    }
    public void setDrugFormId(int drugFormId) {
        this.drugFormId = drugFormId;
    }
    public int getDrugCompanyId() {
        return drugCompanyId;
    }
    public void setDrugCompanyId(int drugCompanyId) {
        this.drugCompanyId = drugCompanyId;
    }
    public String getGeneralDescription() {
        return generalDescription;
    }
    public void setGeneralDescription(String generalDescription) {
        this.generalDescription = generalDescription;
    }


}
