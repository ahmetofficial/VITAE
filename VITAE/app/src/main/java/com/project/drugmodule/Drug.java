// Developer: Ahmet Kaymak
// Date: 25.12.2016

package com.project.drugmodule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Drug {

    public Drug(){};
    public Drug(int drugId, String commercialName) {
        this.drugId = drugId;
        this.commercialName = commercialName;
    }

    //Class Fields
    @SerializedName("drug_id")
    @Expose
    private int drugId;

    @SerializedName("commercial_name")
    @Expose
    private String commercialName;

    @SerializedName("chemical_name")
    @Expose
    private String chemicalName;

    @SerializedName("type_id")
    @Expose
    private String drugTypeId;

    @SerializedName("prescription_type_id")
    @Expose
    private int prescriptionTypeId;

    @SerializedName("form_id")
    @Expose
    private int drugFormId;

    @SerializedName("company_id")
    @Expose
    private int drugCompanyId;

    @SerializedName("general_descriptions")
    @Expose
    private String generalDescription;

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
