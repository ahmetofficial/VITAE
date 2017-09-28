// Developer: Ahmet Kaymak
// Date: 17.02.2017

package com.project.core.drugmodule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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

    @SerializedName("photo_id")
    @Expose
    private int drugFormId;

    @SerializedName("photo_id")
    @Expose
    private String drugFormName;

    @SerializedName("photo_id")
    @Expose
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

