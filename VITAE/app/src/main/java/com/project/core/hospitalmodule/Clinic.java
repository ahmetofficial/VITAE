// Developer: Ahmet Kaymak
// Date: 10.03.2016

package com.project.core.hospitalmodule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Clinic {

    @SerializedName("clinic_id")
    @Expose
    private int clinicId;

    @SerializedName("clinic_name")
    @Expose
    private String clinicName;

    @SerializedName("clinics")
    @Expose
    private ArrayList<Clinic> clinics;

    public int getClinicId() {
        return clinicId;
    }

    public void setClinicId(int clinicId) {
        this.clinicId = clinicId;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public ArrayList<Clinic> getClinics() {
        return clinics;
    }
}
