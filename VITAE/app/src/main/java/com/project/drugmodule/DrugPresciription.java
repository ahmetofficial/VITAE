// Developer: Ahmet Kaymak
// Date: 17.02.2016

package com.project.drugmodule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DrugPresciription {

    public DrugPresciription(int drugPrescriptionTypeId) {
        this.drugPrescriptionTypeId = drugPrescriptionTypeId;
    }
    public DrugPresciription(int drugPrescriptionTypeId, String drugPrescriptionTypeName) {
        this.drugPrescriptionTypeId = drugPrescriptionTypeId;
        this.drugPrescriptionTypeName = drugPrescriptionTypeName;
    }

    @SerializedName("photo_id")
    @Expose
    private int drugPrescriptionTypeId;

    @SerializedName("photo_id")
    @Expose
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
