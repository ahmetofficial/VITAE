package com.project.generalhealthmodule;

import java.util.ArrayList;

public class BloodType {

    private int bloodTypeId;
    private String bloodType;
    private String rhFactor;

    public BloodType(){};
    public BloodType(String bloodType, int bloodTypeId, String rhFactor) {
        this.bloodType = bloodType;
        this.bloodTypeId = bloodTypeId;
        this.rhFactor = rhFactor;
    }

    public int getBloodTypeId() {
        return bloodTypeId;
    }
    public void setBloodTypeId(int bloodTypeId) {
        this.bloodTypeId = bloodTypeId;
    }
    public String getBloodType() {
        return bloodType;
    }
    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }
    public String getRhFactor() {
        return rhFactor;
    }
    public void setRhFactor(String rhFactor) {
        this.rhFactor = rhFactor;
    }
    public ArrayList<BloodType> getBloodTypes(){
        ArrayList<BloodType> bloodTypes=new ArrayList<>();
        BloodType bloodType=new BloodType();
        //code will be added.
        return bloodTypes;
    }
}