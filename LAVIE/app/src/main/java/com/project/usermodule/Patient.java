package com.project.usermodule;

import java.util.Date;

public class Patient extends User {
    private String userLastName;
    private String gender;
    private int bloodTypeId;
    private Date birthday;

    public Patient(){}

    public Patient(String userId,String userName, String userLastName, String mail,String password){
        this.userId=userId;
        this.userName=userName;
        this.userLastName=userLastName;
        this.mail=mail;
        this.password=password;


    }

}
