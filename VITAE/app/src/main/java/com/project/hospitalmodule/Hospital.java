// Developer: Ahmet Kaymak
// Date: 14.04.2017

package com.project.hospitalmodule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;

public class Hospital {

    @SerializedName("hospital_id")
    @Expose
    private int hospitalId;

    @SerializedName("hospital_user_id")
    @Expose
    private String hospitalUserId;

    @SerializedName("hospital_name")
    @Expose
    private String hospitalName;

    @SerializedName("hospital_type")
    @Expose
    private int hospitalType;

    @SerializedName("establishement_date")
    @Expose
    private Date establishmentDate;

    @SerializedName("total_score")
    @Expose
    BigInteger totalScore;

    @SerializedName("total_vote_number")
    @Expose
    int totalVoteNumber;

    @SerializedName("overall_score")
    @Expose
    double overallScore;

    @SerializedName("is_active")
    @Expose
    int isActive;

    @SerializedName("website")
    @Expose
    String website;

    @SerializedName("phone_number")
    @Expose
    String phoneNumber;

    @SerializedName("mail")
    @Expose
    String mail;

    @SerializedName("adress")
    @Expose
    String adress;

    @SerializedName("latitude")
    @Expose
    String latitude;

    @SerializedName("longitude")
    @Expose
    String longitude;

    @SerializedName("created_at")
    @Expose
    Date createdAt;

    @SerializedName("updated_at")
    @Expose
    Date updatedAt;

    @SerializedName("hospitals")
    @Expose
    ArrayList<Hospital> hospitals;

    @SerializedName("hospital")
    @Expose
    Hospital hospital;

    public int getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(int hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getHospitalUserId() {
        return hospitalUserId;
    }

    public void setHospitalUserId(String hospitalUserId) {
        this.hospitalUserId = hospitalUserId;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public int getHospitalType() {
        return hospitalType;
    }

    public void setHospitalType(int hospitalType) {
        this.hospitalType = hospitalType;
    }

    public Date getEstablishmentDate() {
        return establishmentDate;
    }

    public void setEstablishmentDate(Date establishmentDate) {
        this.establishmentDate = establishmentDate;
    }

    public BigInteger getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(BigInteger totalScore) {
        this.totalScore = totalScore;
    }

    public int getTotalVoteNumber() {
        return totalVoteNumber;
    }

    public void setTotalVoteNumber(int totalVoteNumber) {
        this.totalVoteNumber = totalVoteNumber;
    }

    public double getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(double overallScore) {
        this.overallScore = overallScore;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
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

    public ArrayList<Hospital> getHospitals() {
        return hospitals;
    }

    public void setHospitals(ArrayList<Hospital> hospitals) {
        this.hospitals = hospitals;
    }

    public Hospital getHospital() {
        return hospital;
    }
}
