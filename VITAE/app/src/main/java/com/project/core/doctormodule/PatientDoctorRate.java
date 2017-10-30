// Developer: Ahmet Kaymak
// Date: 24.09.2016

package com.project.core.doctormodule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.project.core.diseasemodule.Disease;
import com.project.core.usermodule.User;

import java.util.ArrayList;
import java.util.Date;

public class PatientDoctorRate {

    @SerializedName("patient_id")
    @Expose
    private String patientId;

    @SerializedName("doctor_id")
    @Expose
    private String doctorId;

    @SerializedName("disease_id")
    @Expose
    private String diseaseId;

    @SerializedName("user_rate")
    @Expose
    private double userRate;

    @SerializedName("user_comment")
    @Expose
    private String userComment;

    @SerializedName("doctor_overall_score")
    @Expose
    private double doctorOverallScore;

    @SerializedName("created_at")
    @Expose
    private Date createdAt;

    @SerializedName("updated_at")
    @Expose
    private Date updatedAt;

    @SerializedName("rates")
    @Expose
    private ArrayList<PatientDoctorRate> rates;

    @SerializedName("DOCTOR")
    @Expose
    private DoctorHaveHospital doctor;

    @SerializedName("USER")
    @Expose
    private User user;

    @SerializedName("DISEASE")
    @Expose
    private Disease disease;

    @SerializedName("PATIENT")
    @Expose
    private User patient;

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(String diseaseId) {
        this.diseaseId = diseaseId;
    }

    public double getUserRate() {
        return userRate;
    }

    public void setUserRate(double userRate) {
        this.userRate = userRate;
    }

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public double getDoctorOverallScore() {
        return doctorOverallScore;
    }

    public ArrayList<PatientDoctorRate> getRates() {
        return rates;
    }

    public DoctorHaveHospital getDoctor() {
        return doctor;
    }

    public User getUser() {
        return user;
    }

    public Disease getDisease() {
        return disease;
    }

    public User getPatient() {
        return patient;
    }

}
