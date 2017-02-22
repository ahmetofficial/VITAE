package com.project.drugmodule;

// Developer: Ahmet Kaymak
// Date: 17.02.2017

public class DrugType {
    private String drugTypeId;
    private String drugTypeName;
    private String drugGeneralType;

    public DrugType(String drugTypeId) {
        this.drugTypeId = drugTypeId;
    }
    public DrugType(String drugTypeId, String drugTypeName) {
        this.drugTypeId = drugTypeId;
        this.drugTypeName = drugTypeName;
        this.drugGeneralType = drugTypeName.substring(0,1);
    }

    public String getDrugTypeId() {
        return drugTypeId;
    }
    public void setDrugTypeId(String drugTypeId) {
        this.drugTypeId = drugTypeId;
        this.drugGeneralType = drugTypeName.substring(0,1);
    }
    public String getDrugTypeName() {
        return drugTypeName;
    }
    public void setDrugTypeName(String drugTypeName) {
        this.drugTypeName = drugTypeName;
    }
    public String getDrugGeneralType() {
        return drugGeneralType;
    }
}
