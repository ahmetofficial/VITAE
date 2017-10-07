// Developer: Ahmet Kaymak
// Date: 10.03.2016

package com.project.core.locationmodule;

import java.util.ArrayList;

public class State {

    public State(){}
    public State(int stateId, Country country, String stateName, String stateCode) {
        this.stateId = stateId;
        this.country = country;
        this.stateName = stateName;
        this.stateCode = stateCode;
    }

    private int stateId;
    private Country country;
    private String stateName;
    private String stateCode;

    private ArrayList<Province> stateHasProvinces=new ArrayList<>();

    public String getStateCode() {
        return stateCode;
    }
    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }
    public int getStateId() {
        return stateId;
    }
    public void setStateId(int stateId) {
        this.stateId = stateId;
    }
    public Country getCountry() {
        return country;
    }
    public void setCountry(Country country) {
        this.country = country;
    }
    public String getStateName() {
        return stateName;
    }
    public void setStateName(String stateName) {
        this.stateName = stateName;
    }




}
