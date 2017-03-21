// Developer: Ahmet Kaymak
// Date: 10.03.2016

package com.project.usermodule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class User {

    public User() {
    }

    public User(String userId) {
        this.userId = userId;
    }

    @SerializedName("user_id")
    @Expose
    protected String userId;

    @SerializedName("user_name")
    @Expose
    protected String userName;

    @SerializedName("user_type_id")
    @Expose
    protected int userTypeId;

    @SerializedName("mail")
    @Expose
    protected String mail;

    @SerializedName("password")
    @Expose
    protected String password;

    @SerializedName("mail_activation")
    @Expose
    protected boolean mailActivation;

    @SerializedName("phone_number")
    @Expose
    protected String phoneNumber;

    @SerializedName("about_me")
    @Expose
    protected String aboutMe;

    @SerializedName("friend_count")
    @Expose
    protected int friendCount;

    @SerializedName("is_official_user")
    @Expose
    protected int isOfficialUser;

    @SerializedName("profile_picture_id")
    @Expose
    protected String profilePictureId;

    @SerializedName("created_at")
    @Expose
    protected Date createdAt;

    @SerializedName("updated_at")
    @Expose
    protected Date updatedAt;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(int userTypeId) {
        this.userTypeId = userTypeId;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isMailActivation() {
        return mailActivation;
    }

    public void setMailActivation(boolean mailActivation) {
        this.mailActivation = mailActivation;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public int getFriendCount() {
        return friendCount;
    }

    public void setFriendCount(int friendCount) {
        this.friendCount = friendCount;
    }

    public int isOfficialUser() {
        return isOfficialUser;
    }

    public void setOfficialUser(int officialUser) {
        isOfficialUser = officialUser;
    }

    public String getProfilePictureId() {
        return profilePictureId;
    }

    public void setProfilePictureId(String profilePictureId) {
        this.profilePictureId = profilePictureId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
