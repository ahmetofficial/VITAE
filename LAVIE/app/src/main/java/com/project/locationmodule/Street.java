package com.project.locationmodule;

public class Street {

    public  Street(){ }
    public Street(String streetAdress) {
        this.streetAdress = streetAdress;
    }

    private String streetAdress;

    public String getStreetAdress() {
        return streetAdress;
    }
    public void setStreetAdress(String streetAdress) {
        this.streetAdress = streetAdress;
    }

}
