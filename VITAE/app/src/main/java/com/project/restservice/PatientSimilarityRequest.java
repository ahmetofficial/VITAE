// Developer: Ahmet Kaymak
// Date: 21.07.2017

package com.project.restservice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PatientSimilarityRequest {

    @SerializedName("user_id")
    @Expose
    private String userId;

    @SerializedName("search_text")
    @Expose
    private String searchText;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }
}
