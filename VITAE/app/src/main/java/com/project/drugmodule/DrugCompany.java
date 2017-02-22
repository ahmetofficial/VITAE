package com.project.drugmodule;

// Developer: Ahmet Kaymak
// Date: 17.02.2017

public class DrugCompany {

    public DrugCompany(int drugCompanyId) {
        this.drugCompanyId = drugCompanyId;
    }
    public DrugCompany(int drugCompanyId, String drugCompanyName) {
        this.drugCompanyId = drugCompanyId;
        this.drugCompanyName = drugCompanyName;
    }

    private int drugCompanyId;
    private String drugCompanyName;

    public int getDrugCompanyId() {
        return drugCompanyId;
    }
    public void setDrugCompanyId(int drugCompanyId) {
        this.drugCompanyId = drugCompanyId;
    }
    public String getDrugCompanyName() {
        return drugCompanyName;
    }
    public void setDrugCompanyName(String drugCompanyName) {
        this.drugCompanyName = drugCompanyName;
    }
}
