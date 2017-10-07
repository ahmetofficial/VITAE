// Developer: Ahmet Kaymak
// Date: 10.03.2016

package com.project.core.locationmodule;

import java.util.ArrayList;

public class Province {

    public Province(){}
    public Province(int provinceId,String provinceName,Country country, State state) {
        this.provinceId = provinceId;
        this.provinceName = provinceName;
        this.country = country;
        this.state = state;

    }

    private int provinceId;
    private String provinceName;
    private Country country;
    private State state;

    private ArrayList<City> provincesHaveCities=new ArrayList<>();

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
    public String getProvinceName() {
        return provinceName;
    }
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

}
