package com.project.locationmodule;

public class City {

    public City(){}
    public City(int cityId, String cityName, Country country, State state) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.country = country;
        this.state = state;
    }

    private int cityId;
    private Country country;
    private State state;
    private String cityName;


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

}
