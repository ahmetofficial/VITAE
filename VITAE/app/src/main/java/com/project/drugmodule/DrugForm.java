package com.project.drugmodule;

// Developer: Ahmet Kaymak
// Date: 17.02.2017

public class DrugForm {

    public DrugForm(){}
    public DrugForm(int drugFormId) {
        this.drugFormId = drugFormId;
    }
    public DrugForm(int drugFormId, String drugFormName, String drugFormDescription) {
        this.drugFormId = drugFormId;
        this.drugFormName = drugFormName;
        this.drugFormDescription = drugFormDescription;
    }

    private int drugFormId;
    private String drugFormName;
    private String drugFormDescription;

    public int getDrugFormId() {
        return drugFormId;
    }
    public void setDrugFormId(int drugFormId) {
        this.drugFormId = drugFormId;
    }
    public String getDrugFormName() {
        return drugFormName;
    }
    public void setDrugFormName(String drugFormName) {
        this.drugFormName = drugFormName;
    }
    public String getDrugFormDescription() {
        return drugFormDescription;
    }
    public void setDrugFormDescription(String drugFormDescription) {
        this.drugFormDescription = drugFormDescription;
    }
}

