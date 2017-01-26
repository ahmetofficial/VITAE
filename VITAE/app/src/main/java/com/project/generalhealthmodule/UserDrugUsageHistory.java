package com.project.generalhealthmodule;

import com.project.diseasemodule.Disease;
import com.project.drugmodule.Drug;
import com.project.usermodule.User;
import java.util.Date;

/**
 * Created by Ahmet Kaymak on 26.12.2016.
 */

public class UserDrugUsageHistory {

    public UserDrugUsageHistory(){}
    public UserDrugUsageHistory(User user, Disease disease, Drug drug) {
        this.user = user;
        this.disease = disease;
        this.drug = drug;
    }

    private User user;
    private Disease disease;
    private Drug drug;
    private Date drugUsageStartDate;
    private Date drugUsageSystemRegistrationDate;
    private Date drugUsageFinishDate;
    private int drugEffectOnDisease; //values led between -10 / 10

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
    public Drug getDrug() {
        return drug;
    }
    public void setDrug(Drug drug) {
        this.drug = drug;
    }
    public Date getDrugUsageStartDate() {
        return drugUsageStartDate;
    }
    public void setDrugUsageStartDate(Date drugUsageStartDate) {
        this.drugUsageStartDate = drugUsageStartDate;
    }
    public Date getDrugUsageSystemRegistrationDate() {
        return drugUsageSystemRegistrationDate;
    }
    public void setDrugUsageSystemRegistrationDate(Date drugUsageSystemRegistrationDate) {
        this.drugUsageSystemRegistrationDate = drugUsageSystemRegistrationDate;
    }
    public Date getDrugUsageFinishDate() {
        return drugUsageFinishDate;
    }
    public void setDrugUsageFinishDate(Date drugUsageFinishDate) {
        this.drugUsageFinishDate = drugUsageFinishDate;
    }
    public int getDrugEffectOnDisease() {
        return drugEffectOnDisease;
    }
    public void setDrugEffectOnDisease(int drugEffectOnDisease) {
        this.drugEffectOnDisease = drugEffectOnDisease;
    }

}
