package com.project.locationmodule;

public class Country {

    public Country(){}
    public Country(String countryCode, int id, int countryPhoneCode, String countyName, boolean hasStates) {
        this.countryCode = countryCode;
        this.id = id;
        this.countryPhoneCode = countryPhoneCode;
        this.countyName = countyName;
        this.hasStates = hasStates;
    }

    private int id;
    private String countryCode;
    private int countryPhoneCode;
    private String countyName;
    private boolean hasStates;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCountryCode() {
        return countryCode;
    }
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    public int getCountryPhoneCode() {
        return countryPhoneCode;
    }
    public void setCountryPhoneCode(int countryPhoneCode) {
        this.countryPhoneCode = countryPhoneCode;
    }
    public String getCountyName() {
        return countyName;
    }
    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }
    public boolean isHasStates() {
        return hasStates;
    }
    public void setHasStates(boolean hasStates) {
        this.hasStates = hasStates;
    }

}
