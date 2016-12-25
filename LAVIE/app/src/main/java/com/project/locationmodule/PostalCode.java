package com.project.locationmodule;

public class PostalCode {

    public PostalCode() {}
    public PostalCode(int code) {
        this.code = code;
    }

    private int code;

    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }

}
