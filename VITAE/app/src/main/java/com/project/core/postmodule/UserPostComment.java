// Developer: Ahmet Kaymak
// Date: 16.08.2017

package com.project.core.postmodule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class UserPostComment {

    @SerializedName("post_comment_id")
    @Expose
    private String postCommentId;

    @SerializedName("post_id")
    @Expose
    private String post_id;

    @SerializedName("user_id")
    @Expose
    private String user_id;

    @SerializedName("user_ip")
    @Expose
    private String userIp;

    @SerializedName("comment_text")
    @Expose
    private String commentText;

    @SerializedName("created_at")
    @Expose
    private Date created_at;

    @SerializedName("updated_at")
    @Expose
    private Date updated_at;


    public String getPostCommentId() {
        return postCommentId;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
    
}
