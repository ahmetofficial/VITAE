package com.project.locations;

public class Province {

    public Province(){}
    public Province(int provinceId, Country country, State state, City city, String provinceName) {
        this.provinceId = provinceId;
        this.country = country;
        this.state = state;
        this.city = city;
        this.provinceName = provinceName;
    }

    private int provinceId;
    private Country country;
    private State state;
    private City city;
    private String provinceName;

    public int getProvinceId() {
        return provinceId;
    }
    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
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
    public City getCity() {
        return city;
    }
    public void setCity(City city) {
        this.city = city;
    }
    public String getProvinceName() {
        return provinceName;
    }
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

}
