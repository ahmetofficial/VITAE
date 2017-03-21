// Developer: Ahmet Kaymak
// Date: 22.02.2016

package com.project.postmodule;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.project.restservice.ApiClient;
import com.project.restservice.ServerResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserPost {

    public UserPost() {
    }

    public UserPost(int post_id, String user_id, String post_text) {
        this.post_id = post_id;
        this.user_id = user_id;
        this.post_text = post_text;
    }

    @SerializedName("post_id")
    @Expose
    private int post_id;

    @SerializedName("user_id")
    @Expose
    private String user_id;

    @SerializedName("post_text")
    @Expose
    private String post_text;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("created_at")
    @Expose
    private Date created_at;

    @SerializedName("updated_at")
    @Expose
    private Date updated_at;

    @SerializedName("posts")
    @Expose
    private ArrayList<UserPost> posts;

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPost_text() {
        return post_text;
    }

    public void setPost_text(String post_text) {
        this.post_text = post_text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public List<UserPost> getPosts() {
        return posts;
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
