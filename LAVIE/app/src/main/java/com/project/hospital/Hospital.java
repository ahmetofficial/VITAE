package com.project.hospital;

import com.project.locations.Adress;

import java.util.ArrayList;
import java.util.Date;

public class Hospital {

    public Hospital(){}
    public Hospital(int hospitalId, String hospitalUserId, String hospitalName, String hospitalType, boolean isActive, Date establishmentDate, Adress adress, String website, String phoneNumber) {
        this.hospitalId = hospitalId;
        this.hospitalUserId = hospitalUserId;
        this.hospitalName = hospitalName;
        this.hospitalType = hospitalType;
        this.isActive = isActive;
        this.establishmentDate = establishmentDate;
        this.adress = adress;
        this.website = website;
        this.phoneNumber = phoneNumber;
    }

    private int hospitalId;
    private String hospitalUserId;
    private String hospitalName;
    private String hospitalType;
    private Adress adress;
    private Date establishmentDate;
    private boolean isActive;
    private String website;
    private String phoneNumber;
    private ArrayList<Clinic> hospitalClinics;

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
    public String getHospitalType() {
        return hospitalType;
    }
    public void setHospitalType(String hospitalType) {
        this.hospitalType = hospitalType;
    }
    public Adress getAdress() {
        return adress;
    }
    public void setAdress(Adress adress) {
        this.adress = adress;
    }
    public Date getEstablishmentDate() {
        return establishmentDate;
    }
    public void setEstablishmentDate(Date establishmentDate) {
        this.establishmentDate = establishmentDate;
    }
    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean active) {
        isActive = active;
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
    public ArrayList<Clinic> getHospitalClinics() {
        return hospitalClinics;
    }
    public void setHospitalClinics(ArrayList<Clinic> hospitalClinics) {
        this.hospitalClinics = hospitalClinics;
    }

}
