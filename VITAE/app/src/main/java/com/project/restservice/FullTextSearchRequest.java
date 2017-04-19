package com.project.restservice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FullTextSearchRequest {

    @SerializedName("search_text")
    @Expose
    private String searchText;

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }
}
