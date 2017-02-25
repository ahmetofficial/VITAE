// Developer: Ahmet Kaymak
// Date: 22.02.2016

package com.project.postmodule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserPostHavePhotos {

    UserPostHavePhotos(){}
    public UserPostHavePhotos(int post_id, int photo_id) {
        this.post_id = post_id;
        this.photo_id = photo_id;
    }

    @SerializedName("post_id")
    @Expose
    private int post_id;

    @SerializedName("photo_id")
    @Expose
    private int photo_id;

    public int getPost_id() {
        return post_id;
    }
    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }
    public int getPhoto_id() {
        return photo_id;
    }
    public void setPhoto_id(int photo_id) {
        this.photo_id = photo_id;
    }

}
