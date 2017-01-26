package com.project.generalhealthmodule;

import com.project.diseasemodule.Disease;
import com.project.usermodule.User;

import java.util.Date;

/**
 * Created by Ahmet Kaymak on 25.12.2016.
 */

public class UserHealthHistory {

    public UserHealthHistory(){};
    public UserHealthHistory(User user, Disease disease) {
        this.user = user;
        this.disease = disease;
    }

    private User user;
    private Disease disease;
    private Date diseaseDiagnosisDate;
    private Date diseaseSystemRegistrationDate;
    private Date diseaseFinishDate;
    private String diseaseLevel;
    private String diseaseState;

    public Date getDiseaseSystemRegistrationDate() {
        return diseaseSystemRegistrationDate;
    }
    public void setDiseaseSystemRegistrationDate(Date diseaseSystemRegistrationDate) {
        this.diseaseSystemRegistrationDate = diseaseSystemRegistrationDate;
    }
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
    public Date getDiseaseDiagnosisDate() {
        return diseaseDiagnosisDate;
    }
    public void setDiseaseDiagnosisDate(Date diseaseDiagnosisDate) {
        this.diseaseDiagnosisDate = diseaseDiagnosisDate;
    }
    public Date getDiseaseFinishDate() {
        return diseaseFinishDate;
    }
    public void setDiseaseFinishDate(Date diseaseFinishDate) {
        this.diseaseFinishDate = diseaseFinishDate;
    }
    public String getDiseaseLevel() {
        return diseaseLevel;
    }
    public void setDiseaseLevel(String diseaseLevel) {
        this.diseaseLevel = diseaseLevel;
    }
    public String getDiseaseState() {
        return diseaseState;
    }
    public void setDiseaseState(String diseaseState) {
        this.diseaseState = diseaseState;
    }

}
