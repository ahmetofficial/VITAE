// Developer: Ahmet Kaymak
// Date: 10.03.2016

package com.project.usermodule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;

public class User {

    public User() {
    }

    public User(String userId) {
        this.userId = userId;
    }

    @SerializedName("user_id")
    @Expose
    private String userId;

    @SerializedName("user_name")
    @Expose
    private String userName;

    @SerializedName("user_type_id")
    @Expose
    private int userTypeId;

    @SerializedName("mail")
    @Expose
    private String mail;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("mail_activation")
    @Expose
    private boolean mailActivation;

    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;

    @SerializedName("about_me")
    @Expose
    private String aboutMe;

    @SerializedName("friend_count")
    @Expose
    private int friendCount;

    @SerializedName("is_official_user")
    @Expose
    private int isOfficialUser;

    @SerializedName("profile_picture_id")
    @Expose
    private String profilePictureId;

    @SerializedName("created_at")
    @Expose
    private Date createdAt;

    @SerializedName("updated_at")
    @Expose
    private Date updatedAt;

    @SerializedName("users")
    @Expose
    private ArrayList<User> users;

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

    public ArrayList<User> getUsers() {
        return users;
    }
    
}
