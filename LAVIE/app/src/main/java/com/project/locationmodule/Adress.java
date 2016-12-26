package com.project.locationmodule;

public class Adress {

    public Adress(){}
    public Adress(Country country, State state, Province province, City city, Street street) {
        this.country = country;
        this.state = state;
        this.province = province;
        this.city = city;
        this.street = street;
    }

    private Country country;
    private State state;
    private Province province;
    private City city;
    private Street street;

    //generic methods will be added for getting adresses of different objects.

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
    public City getCity() {
        return city;
    }
    public void setCity(City city) {
        this.city = city;
    }
    public Street getStreet() {
        return street;
    }
    public void setStreet(Street street) {
        this.street = street;
    }

}
