// Developer: Ahmet Kaymak
// Date: 14.08.2017

package com.project.core.messagemodule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;

public class Conversation {

    @SerializedName("conversation_id")
    @Expose
    private String conversationId;

    @SerializedName("sender_id")
    @Expose
    private String senderId;

    @SerializedName("receiver_id")
    @Expose
    private String receiverId;

    @SerializedName("user_ip")
    @Expose
    private String senderIp;

    @SerializedName("conversation_active_for_sender")
    @Expose
    private int conversationActiveForSender;

    @SerializedName("conversation_active_for_receiver")
    @Expose
    private int conversationActiveForReceiver;

    @SerializedName("created_at")
    @Expose
    private Date createdAt;

    @SerializedName("updated_at")
    @Expose
    private Date updatedAt;

    @SerializedName("conversation")
    @Expose
    private ArrayList<Conversation> conversations;

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getSenderIp() {
        return senderIp;
    }

    public int getConversationActiveForSender() {
        return conversationActiveForSender;
    }

    public void setConversationActiveForSender(int conversationActiveForSender) {
        this.conversationActiveForSender = conversationActiveForSender;
    }

    public int getConversationActiveForReceiver() {
        return conversationActiveForReceiver;
    }

    public void setConversationActiveForReceiver(int conversationActiveForReceiver) {
        this.conversationActiveForReceiver = conversationActiveForReceiver;
    }

    public void setSenderIp(String senderIp) {
        this.senderIp = senderIp;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public ArrayList<Conversation> getConversations() {
        return conversations;
    }
}
