// Developer: Ahmet Kaymak
// Date: 21.10.2017

package com.project.core.patientmodule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.project.core.messagemodule.Conversation;

import java.util.ArrayList;

public class PatientForConversation extends Patient {

    @SerializedName("users_for_conversation")
    @Expose
    private ArrayList<PatientForConversation> patients;

    @SerializedName("SENDER")
    @Expose
    private ArrayList<Conversation>sender;

    @SerializedName("RECEIVER")
    @Expose
    private ArrayList<Conversation>receiver;

    @SerializedName("conversation_id")
    @Expose
    private String conversationId;

    public ArrayList<PatientForConversation> getPatientsForConversation() {
        return patients;
    }

    public ArrayList<Conversation> getSender() {
        return sender;
    }

    public ArrayList<Conversation> getReceiver() {
        return receiver;
    }

    public String getConversationId() {
        return conversationId;
    }
}
