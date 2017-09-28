package com.project.core.generalhealthmodule;

import java.util.ArrayList;

/**
 * Created by Ahmet Kaymak on 25.12.2016.
 */

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
