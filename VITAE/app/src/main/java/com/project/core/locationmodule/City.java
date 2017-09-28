package com.project.core.locationmodule;

import java.util.ArrayList;

public class City {

    public City(){}
    public City(int cityId, Country country, String cityName, State state, Province province) {
        this.cityId = cityId;
        this.country = country;
        this.cityName = cityName;
        this.state = state;
        this.province = province;
    }

    private int cityId;
    private String cityName;
    private Country country;
    private State state;
    private Province province;

    private ArrayList<District> cityHaveDistricts=new ArrayList<>();

    public int getCityId() {
        return cityId;
    }
    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
    public String getCityName() {
        return cityName;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    public Country getCountry() {
        return country;
    }
    public void setCountry(Country country) {
        this.country = country;
    }
    public State getState() {
        return state;
    }
    public void setState(State state) {
        this.state = state;
    }
    public Province getProvince() {
        return province;
    }
    public void setProvince(Province province) {
        this.province = province;
    }

}
