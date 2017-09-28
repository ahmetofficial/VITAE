package com.project.core.hospitalmodule;

import com.project.core.diseasemodule.Disease;

import java.util.ArrayList;

public class Branch {

    public Branch(){}
    public Branch(int branchId, String branchName) {
        this.branchId = branchId;
        this.branchName = branchName;
    }

    private int branchId;
    private String branchName;

    ArrayList<Disease> branchHaveDiseases=new ArrayList<>();

    public int getBranchId() {
        return branchId;
    }
    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }
    public String getBranchName() {
        return branchName;
    }
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

}
