// Developer: Ahmet Kaymak
// Date: 13.10.2016

package com.project.core.generalhealthmodule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.project.core.hospitalmodule.Hospital;

import java.util.ArrayList;
import java.util.Date;

public class BloodAlarm {

    @SerializedName("blood_alarm_id")
    @Expose
    private String bloodAlarmId;

    @SerializedName("user_id")
    @Expose
    private String userId;

    @SerializedName("blood_type_id")
    @Expose
    private int bloodTypeId;

    @SerializedName("hospital_id")
    @Expose
    private int hospitalId;

    @SerializedName("alarm_status")
    @Expose
    private int alarmStatus;

    @SerializedName("alarm_level")
    @Expose
    private int alarmLevel;

    @SerializedName("contact_number")
    @Expose
    private String contactNumber;

    @SerializedName("alarm_result")
    @Expose
    private int alarmResult;

    @SerializedName("user_review")
    @Expose
    private String userReview;

    @SerializedName("created_at")
    @Expose
    private Date created_at;

    @SerializedName("updated_at")
    @Expose
    private Date updated_at;

    @SerializedName("BLOOD_TYPE")
    @Expose
    private BloodType bloodType;

    @SerializedName("HOSPITAL")
    @Expose
    private Hospital hospital;

    @SerializedName("blood_alarms")
    @Expose
    private ArrayList<BloodAlarm> bloodAlarms;

    public String getBloodAlarmId() {
        return bloodAlarmId;
    }

    public void setBloodAlarmId(String bloodAlarmId) {
        this.bloodAlarmId = bloodAlarmId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getBloodTypeId() {
        return bloodTypeId;
    }

    public void setBloodTypeId(int bloodTypeId) {
        this.bloodTypeId = bloodTypeId;
    }

    public int getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(int hospitalId) {
        this.hospitalId = hospitalId;
    }

    public int getAlarmStatus() {
        return alarmStatus;
    }

    public void setAlarmStatus(int alarmStatus) {
        this.alarmStatus = alarmStatus;
    }

    public int getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(int alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public int getAlarmResult() {
        return alarmResult;
    }

    public void setAlarmResult(int alarmResult) {
        this.alarmResult = alarmResult;
    }

    public String getUserReview() {
        return userReview;
    }

    public void setUserReview(String userReview) {
        this.userReview = userReview;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public ArrayList<BloodAlarm> getBloodAlarms() {
        return bloodAlarms;
    }

}