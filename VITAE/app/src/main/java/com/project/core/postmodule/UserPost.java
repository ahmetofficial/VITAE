// Developer: Ahmet Kaymak
// Date: 22.02.2016

package com.project.core.postmodule;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.project.core.usermodule.User;
import com.project.restservice.ApiClient;
import com.project.restservice.serverresponse.ServerResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserPost {

    @SerializedName("post_id")
    @Expose
    private String postId;

    @SerializedName("user_id")
    @Expose
    private String userId;

    @SerializedName("post_text")
    @Expose
    private String postText;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("like_count")
    @Expose
    private int likeCount;

    @SerializedName("user_ip")
    @Expose
    private String userIp;

    @SerializedName("comment_count")
    @Expose
    private int commentCount;

    @SerializedName("created_at")
    @Expose
    private Date createdAt;

    @SerializedName("updated_at")
    @Expose
    private Date updatedAt;

    @SerializedName("posts")
    @Expose
    private ArrayList<UserPost> posts;

    @SerializedName("USER_POST_LIKEs")
    @Expose
    private ArrayList<UserPostLike> postLikes;

    @SerializedName("USER_POST_HAVE_PHOTOs")
    @Expose
    private ArrayList<UserPostHavePhotos> userPostHavePhotos;

    @SerializedName("USER")
    @Expose
    private User user;

    public String getPostId() {
        return postId;
    }

    public void setPostId(String post_id) {
        this.postId = post_id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String user_id) {
        this.userId = user_id;
    }

    public String getPostText() {
        return postText;
    }

    public void setPost_text(String post_text) {
        this.postText = post_text;
    }

    public String getUrl() {
        return url;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date created_at) {
        this.createdAt = created_at;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updated_at) {
        this.updatedAt = updated_at;
    }

    public List<UserPost> getPosts() {
        return posts;
    }

    public ArrayList<UserPostLike> getPostLikes() {
        return postLikes;
    }

    public User getUser() {
        return user;
    }

    public ArrayList<UserPostHavePhotos> getUserPostHavePhotos() {
        return userPostHavePhotos;
    }

    public static boolean createUserPost(String userId, UserPost userPost, final Activity activity) {
        final boolean[] isTransactionSuccesful = {false};
        try {
            ApiClient.postApi().createNewPost( userId, userPost ).enqueue( new Callback<ServerResponse>() {
                @Override
                public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equals( "true" )) {
                            isTransactionSuccesful[0] =true;
                        }
                    }
                }

                @Override
                public void onFailure(Call<ServerResponse> call, Throwable t) {
                    Log.e( "UserTimeline", t.getMessage() );
                    Toast.makeText( activity.getBaseContext(), t.getMessage(), Toast.LENGTH_LONG ).show();
                    isTransactionSuccesful[0] =false;
                }
            } );
        } catch (Exception e) {
            Log.e( "UserTimeline", e.getMessage() );
            Toast.makeText( activity.getBaseContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
            isTransactionSuccesful[0] =false;
        }
        return  isTransactionSuccesful[0];
    }

}
