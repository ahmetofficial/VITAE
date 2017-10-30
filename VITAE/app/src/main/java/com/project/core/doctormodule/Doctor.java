// Developer: Ahmet Kaymak
// Date: 10.03.2016

package com.project.core.doctormodule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.project.core.usermodule.User;

import java.util.ArrayList;
import java.util.Date;

public class Doctor extends User {
    @SerializedName("gender")
    @Expose
    private int gender;

    @SerializedName("blood_type_id")
    @Expose
    private int bloodTypeId;

    @SerializedName("birthday")
    @Expose
    private Date birthday;

    @SerializedName("is_verified")
    @Expose
    private int isVerified;

    @SerializedName("total_score")
    @Expose
    private int totalScore;

    @SerializedName("total_vote_number")
    @Expose
    private int totalVoteNumber;

    @SerializedName("vote_1_count")
    @Expose
    private int vote1Count;

    @SerializedName("vote_2_count")
    @Expose
    private int vote2Count;

    @SerializedName("vote_3_count")
    @Expose
    private int vote3Count;

    @SerializedName("vote_4_count")
    @Expose
    private int vote4Count;

    @SerializedName("vote_5_count")
    @Expose
    private int vote5Count;

    @SerializedName("DOCTORs")
    @Expose
    private ArrayList<DoctorRating> doctors;

    @SerializedName("DOCTOR_HAVE_HOSPITALs")
    @Expose
    private ArrayList<DoctorHaveHospital> doctorHaveHospitals;

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getBloodTypeId() {
        return bloodTypeId;
    }

    public void setBloodTypeId(int bloodTypeId) {
        this.bloodTypeId = bloodTypeId;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getIsVerified() {
        return isVerified;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public int getTotalVoteNumber() {
        return totalVoteNumber;
    }

    public int getVote1Count() {
        return vote1Count;
    }

    public int getVote2Count() {
        return vote2Count;
    }

    public int getVote3Count() {
        return vote3Count;
    }

    public int getVote4Count() {
        return vote4Count;
    }

    public int getVote5Count() {
        return vote5Count;
    }

    public ArrayList<DoctorRating> getDoctors() {
        return doctors;
    }

    public ArrayList<DoctorHaveHospital> getDoctorHaveHospitals() {
        return doctorHaveHospitals;
    }
}
