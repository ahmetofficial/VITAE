// Developer: Ahmet Kaymak
// Date: 10.03.2016

package com.project.core.doctormodule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.project.core.hospitalmodule.Clinic;
import com.project.core.hospitalmodule.Hospital;

import java.util.ArrayList;

public class DoctorHaveHospital{

    @SerializedName("user_id")
    @Expose
    private String userId;

    @SerializedName("user_name")
    @Expose
    private String userName;

    @SerializedName("profile_picture_id")
    @Expose
    private String profilePictureId;

    @SerializedName("hospital_id")
    @Expose
    private int hospitalId;

    @SerializedName("clinic_id")
    @Expose
    private int clinicId;

    @SerializedName("HOSPITAL")
    @Expose
    private Hospital hospital;

    @SerializedName("CLINIC")
    @Expose
    private Clinic clinic;

    @SerializedName("DOCTOR_HAVE_HOSPITALs")
    @Expose
    private ArrayList<DoctorHaveHospital> doctorHaveHospitals;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(int hospitalId) {
        this.hospitalId = hospitalId;
    }

    public int getClinicId() {
        return clinicId;
    }

    public void setClinicId(int clinicId) {
        this.clinicId = clinicId;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public String getUserName() {
        return userName;
    }

    public String getProfilePictureId() {
        return profilePictureId;
    }

    public ArrayList<DoctorHaveHospital> getDoctorHaveHospitals() {
        return doctorHaveHospitals;
    }
}
