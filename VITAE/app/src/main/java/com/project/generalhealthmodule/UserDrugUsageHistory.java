// Developer: Ahmet Kaymak
// Date: 31.03.2017

package com.project.generalhealthmodule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDrugUsageHistory {

    @SerializedName("user_id")
    @Expose
    private String userId;

    @SerializedName("disease_id")
    @Expose
    private String diseaseId;

    @SerializedName("disease_count")
    @Expose
    private int diseaseCount;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(String diseaseId) {
        this.diseaseId = diseaseId;
    }

    public int getDiseaseCount() {
        return diseaseCount;
    }
}
