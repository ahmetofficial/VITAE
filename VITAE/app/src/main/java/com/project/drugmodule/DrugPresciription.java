package com.project.drugmodule;

// Developer: Ahmet Kaymak
// Date: 17.02.2016

public class DrugPresciription {

    public DrugPresciription(int drugPrescriptionTypeId) {
        this.drugPrescriptionTypeId = drugPrescriptionTypeId;
    }
    public DrugPresciription(int drugPrescriptionTypeId, String drugPrescriptionTypeName) {
        this.drugPrescriptionTypeId = drugPrescriptionTypeId;
        this.drugPrescriptionTypeName = drugPrescriptionTypeName;
    }

    private int drugPrescriptionTypeId;
    private String drugPrescriptionTypeName;

    public int getDrugPrescriptionTypeId() {
        return drugPrescriptionTypeId;
    }
    public void setDrugPrescriptionTypeId(int drugPrescriptionTypeId) {
        this.drugPrescriptionTypeId = drugPrescriptionTypeId;
    }
    public String getDrugPrescriptionTypeName() {
        return drugPrescriptionTypeName;
    }
    public void setDrugPrescriptionTypeName(String drugPrescriptionTypeName) {
        this.drugPrescriptionTypeName = drugPrescriptionTypeName;
    }
}
