// Developer: Ahmet Kaymak
// Date: 17.02.2017

package com.project.drugmodule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DrugCompany {

    public DrugCompany(int drugCompanyId) {
        this.drugCompanyId = drugCompanyId;
    }
    public DrugCompany(int drugCompanyId, String drugCompanyName) {
        this.drugCompanyId = drugCompanyId;
        this.drugCompanyName = drugCompanyName;
    }

    @SerializedName("photo_id")
    @Expose
    private int drugCompanyId;

    @SerializedName("photo_id")
    @Expose
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
