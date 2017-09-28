// Developer: Ahmet Kaymak
// Date: 21.07.2017

package com.project.core.usermodule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UserRelationship {

    @SerializedName("active_user_id")
    @Expose
    private String activeUserId;

    @SerializedName("passive_user_id")
    @Expose
    private String passiveUserId;

    @SerializedName("relationship")
    @Expose
    private ArrayList<UserRelationship> relationship;

    public String getActiveUserId() {
        return activeUserId;
    }

    public void setActiveUserId(String activeUserId) {
        this.activeUserId = activeUserId;
    }

    public String getPassiveUserId() {
        return passiveUserId;
    }

    public void setPassiveUserId(String passiveUserId) {
        this.passiveUserId = passiveUserId;
    }

    public ArrayList<UserRelationship> getRelationship() {
        return relationship;
    }
}
