// Developer: Ahmet Kaymak
// Date: 14.08.2017

package com.project.core.messagemodule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;

public class Message {

    public static final int TYPE_MESSAGE = 0;
    public static final int TYPE_LOG = 1;
    public static final int TYPE_ACTION = 2;

    @SerializedName("conversation_id")
    @Expose
    private String conversationId;

    @SerializedName("sender_id")
    @Expose
    private String senderId;

    @SerializedName("receiver_id")
    @Expose
    private String receiverId;

    @SerializedName("message_id")
    @Expose
    private String messageId;

    @SerializedName("sender_ip")
    @Expose
    private String senderIp;

    @SerializedName("message_text")
    @Expose
    private String messageText;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message_active_for_sender")
    @Expose
    private String isMessageActiveForSender;

    @SerializedName("message_active_for_receiver")
    @Expose
    private String isMessageActiveForReceiver;

    @SerializedName("created_at")
    @Expose
    private Date createdAt;

    @SerializedName("updated_at")
    @Expose
    private Date updatedAt;

    @SerializedName("messages")
    @Expose
    private ArrayList<Message> messages;

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

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getSenderIp() {
        return senderIp;
    }

    public void setSenderIp(String senderIp) {
        this.senderIp = senderIp;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsMessageActiveForSender() {
        return isMessageActiveForSender;
    }

    public void setIsMessageActiveForSender(String isMessageActiveForSender) {
        this.isMessageActiveForSender = isMessageActiveForSender;
    }

    public String getIsMessageActiveForReceiver() {
        return isMessageActiveForReceiver;
    }

    public void setIsMessageActiveForReceiver(String isMessageActiveForReceiver) {
        this.isMessageActiveForReceiver = isMessageActiveForReceiver;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }
}
