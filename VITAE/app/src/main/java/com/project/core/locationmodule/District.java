// Developer: Ahmet Kaymak
// Date: 10.03.2016

package com.project.core.locationmodule;

public class District {

    public District(){}

    public District(int districtId, String districtName, int postalCode, Country country, State state, Province province, City city) {
        DistrictId = districtId;
        this.districtName = districtName;
        this.postalCode = postalCode;
        this.country = country;
        this.state = state;
        this.province = province;
        this.city = city;
    }

    private int DistrictId;
    private String districtName;
    private int postalCode;
    private Country country;
    private State state;
    private Province province;
    private City city;


    public int getDistrictId() {
        return DistrictId;
    }
    public void setDistrictId(int districtId) {
        DistrictId = districtId;
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
    public Province getProvince() {
        return province;
    }
    public void setProvince(Province province) {
        this.province = province;
    }
    public String getDistrictName() {
        return districtName;
    }
    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }
    public int getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

}
