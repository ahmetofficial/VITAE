// Developer: Ahmet Kaymak
// Date: 18.02.2016

package com.project.restservice.serverResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServerResponseWithPhotoId {

    @SerializedName("photo_id")
    @Expose
    private String photoId;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;

    public String getPhotoId() {
        return photoId;
    }
    public String getStatus() {
        return status;
    }
    public String getMessage() {
        return message;
    }

}
