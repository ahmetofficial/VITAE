// Developer: Ahmet Kaymak
// Date: 10.03.2016

package com.project.core.locationmodule;

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
