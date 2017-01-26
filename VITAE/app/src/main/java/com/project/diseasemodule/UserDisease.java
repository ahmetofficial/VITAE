package com.project.diseasemodule;

import com.project.usermodule.User;

import java.util.Date;

/**
 * Created by Ahmet Kaymak on 25.12.2016.
 */

public class UserDisease {

    public UserDisease(){};
    public UserDisease(User user, Disease disease, String diseaseLevel, Date diagnosisDate) {
        this.user = user;
        this.disease = disease;
        this.diseaseLevel = diseaseLevel;
        this.diagnosisDate = diagnosisDate;
    }

    private User user;
    private Disease disease;
    private String diseaseLevel;
    private Date diagnosisDate;

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Disease getDisease() {
        return disease;
    }
    public void setDisease(Disease disease) {
        this.disease = disease;
    }
    public String getDiseaseLevel() {
        return diseaseLevel;
    }
    public void setDiseaseLevel(String diseaseLevel) {
        this.diseaseLevel = diseaseLevel;
    }
    public Date getDiagnosisDate() {
        return diagnosisDate;
    }
    public void setDiagnosisDate(Date diagnosisDate) {
        this.diagnosisDate = diagnosisDate;
    }

}
