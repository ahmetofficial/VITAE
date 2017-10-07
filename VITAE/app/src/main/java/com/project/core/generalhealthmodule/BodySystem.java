// Developer: Ahmet Kaymak
// Date: 10.03.2016

package com.project.core.generalhealthmodule;

import java.util.ArrayList;

public class BodySystem {

    public BodySystem(){};
    public BodySystem(int bodySystemId, String bodySystemName) {
        this.bodySystemId = bodySystemId;
        this.bodySystemName = bodySystemName;
    }

    private int bodySystemId;
    private String bodySystemName;

    private ArrayList<BodySystem> bodySystemHaveOrgans=new ArrayList<>();

    public int getBodySystemId() {
        return bodySystemId;
    }
    public void setBodySystemId(int bodySystemId) {
        this.bodySystemId = bodySystemId;
    }
    public String getBodySystemName() {
        return bodySystemName;
    }
    public void setBodySystemName(String bodySystemName) {
        this.bodySystemName = bodySystemName;
    }

}
