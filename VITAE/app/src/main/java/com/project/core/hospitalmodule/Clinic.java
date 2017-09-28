package com.project.core.hospitalmodule;

import com.project.core.diseasemodule.Disease;

import java.util.ArrayList;

public class Clinic {

    public Clinic(){}
    public Clinic(int clinicId, String clinicName, ArrayList<Branch> clinicBranches, String description) {
        this.clinicId = clinicId;
        this.clinicName = clinicName;
        this.clinicBranches = clinicBranches;
        this.description = description;
    }

    private int clinicId;
    private String clinicName;
    private ArrayList<Branch> clinicBranches;
    private String description;

    private ArrayList<Disease> clinicHaveDiseases=new ArrayList();
    private ArrayList<Branch> clinicHaveBranches=new ArrayList<>();

    public int getClinicId() {
        return clinicId;
    }
    public void setClinicId(int clinicId) {
        this.clinicId = clinicId;
    }
    public String getClinicName() {
        return clinicName;
    }
    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }
    public ArrayList<Branch> getClinicBranches() {
        return clinicBranches;
    }
    public void setClinicBranches(ArrayList<Branch> clinicBranches) {
        this.clinicBranches = clinicBranches;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

}
