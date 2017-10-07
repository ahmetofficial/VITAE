// Developer: Ahmet Kaymak
// Date: 10.03.2016

package com.project.core.generalhealthmodule;

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
