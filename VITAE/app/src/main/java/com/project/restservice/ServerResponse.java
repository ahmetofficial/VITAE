// Developer: Ahmet Kaymak
// Date: 18.02.2016

package com.project.restservice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServerResponse {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;

    public String getStatus() {
        return status;
    }
    public String getMessage() {
        return message;
    }

}
