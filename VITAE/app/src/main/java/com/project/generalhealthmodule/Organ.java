package com.project.generalhealthmodule;

/**
 * Created by Ahmet Kaymak on 25.12.2016.
 */

public class Organ {

    public Organ(){};
    public Organ(int organId, String organName) {
        this.organId = organId;
        this.organName = organName;
    }

    private int organId;
    private String organName;

    public int getOrganId() {
        return organId;
    }
    public void setOrganId(int organId) {
        this.organId = organId;
    }
    public String getOrganName() {
        return organName;
    }
    public void setOrganName(String organName) {
        this.organName = organName;
    }

}
