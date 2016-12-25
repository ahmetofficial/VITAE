package com.project.generalhealthmodule;

import com.project.diseasemodule.Disease;
import com.project.drugmodule.Drug;
import com.project.threatmentmodule.Threatment;
import com.project.usermodule.User;

/**
 * Created by Ahmet Kaymak on 25.12.2016.
 */

public class UserHealthHistory {

    public UserHealthHistory(){};
    public UserHealthHistory(User user, Disease disease, String diseaseState, Threatment threatment, Drug drug) {
        this.user = user;
        this.disease = disease;
        this.diseaseState = diseaseState;
        this.threatment = threatment;
        this.drug = drug;
    }

    private User user;
    private Disease disease;
    private String diseaseState;
    private Threatment threatment;
    private Drug drug;

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
    public String getDiseaseState() {
        return diseaseState;
    }
    public void setDiseaseState(String diseaseState) {
        this.diseaseState = diseaseState;
    }
    public Threatment getThreatment() {
        return threatment;
    }
    public void setThreatment(Threatment threatment) {
        this.threatment = threatment;
    }
    public Drug getDrug() {
        return drug;
    }
    public void setDrug(Drug drug) {
        this.drug = drug;
    }

}
