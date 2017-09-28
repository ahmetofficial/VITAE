package com.project.core.drugmodule;

// Developer: Ahmet Kaymak
// Date: 17.02.2017

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DrugType {

    public DrugType(String drugTypeId) {
        this.drugTypeId = drugTypeId;
    }
    public DrugType(String drugTypeId, String drugTypeName) {
        this.drugTypeId = drugTypeId;
        this.drugTypeName = drugTypeName;
        this.drugGeneralType = drugTypeName.substring(0,1);
    }

    @SerializedName("photo_id")
    @Expose
    private String drugTypeId;

    @SerializedName("photo_id")
    @Expose
    private String drugTypeName;

    @SerializedName("photo_id")
    @Expose
    private String drugGeneralType;

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
