// Developer: Ahmet Kaymak
// Date: 06.10.2017

package com.project.core.postmodule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserPostHavePhotos {

    @SerializedName("photo_id")
    @Expose
    private String photoId;

    @SerializedName("post_id")
    @Expose
    private String postId;

    public String getPhotoId() {
        return photoId;
    }

    public String getPostId() {
        return postId;
    }

}
