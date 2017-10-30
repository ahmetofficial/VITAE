// Developer: Ahmet Kaymak
// Date: 18.02.2016

package com.project.restservice.serverResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServerResponse {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("conversation_id")
    @Expose
    private String conversationId;

    @SerializedName("user_type_id")
    @Expose
    private int userTypeId;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getConversationId() {
        return conversationId;
    }

    public int getUserTypeId() {
        return userTypeId;
    }
}
