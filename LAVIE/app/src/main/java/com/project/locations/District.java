package com.project.locations;

public class District {

    public District(){}
    public District(int districtId, Country country, State state, City city, String districtName, Province province, PostalCode postalCode) {
        DistrictId = districtId;
        this.country = country;
        this.state = state;
        this.city = city;
        this.districtName = districtName;
        this.province = province;
        this.postalCode = postalCode;
    }

    private int DistrictId;
    private Country country;
    private State state;
    private City city;
    private Province province;
    private String districtName;
    private PostalCode postalCode;

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
    public PostalCode getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(PostalCode postalCode) {
        this.postalCode = postalCode;
    }

}
