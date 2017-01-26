package com.project.generalhealthmodule;

import com.project.diseasemodule.Disease;
import com.project.treatmentmodule.Treatment;
import com.project.usermodule.User;
import java.util.Date;

/**
 * Created by Ahmet Kaymak on 26.12.2016.
 */

public class UserTreatmentHistory {

    public UserTreatmentHistory(){}
    public UserTreatmentHistory(User user, Disease disease, Treatment treatment) {
        this.user = user;
        this.disease = disease;
        this.treatment = treatment;
    }

    private User user;
    private Disease disease;
    private Treatment treatment;
    private Date treatmentStartDate;
    private Date treatmentSystemRegistrationDate;
    private Date treatmentFinishDate;
    private int treatmentEffectOnDisease; //values led between -10 / 10

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
    public Treatment getTreatment() {
        return treatment;
    }
    public void setTreatment(Treatment treatment) {
        this.treatment = treatment;
    }
    public Date getTreatmentStartDate() {
        return treatmentStartDate;
    }
    public void setTreatmentStartDate(Date treatmentStartDate) {
        this.treatmentStartDate = treatmentStartDate;
    }
    public Date getTreatmentSystemRegistrationDate() {
        return treatmentSystemRegistrationDate;
    }
    public void setTreatmentSystemRegistrationDate(Date treatmentSystemRegistrationDate) {
        this.treatmentSystemRegistrationDate = treatmentSystemRegistrationDate;
    }
    public Date getTreatmentFinishDate() {
        return treatmentFinishDate;
    }
    public void setTreatmentFinishDate(Date treatmentFinishDate) {
        this.treatmentFinishDate = treatmentFinishDate;
    }
    public int getTreatmentEffectOnDisease() {
        return treatmentEffectOnDisease;
    }
    public void setTreatmentEffectOnDisease(int treatmentEffectOnDisease) {
        this.treatmentEffectOnDisease = treatmentEffectOnDisease;
    }
}
