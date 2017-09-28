// Developer: Ahmet Kaymak
// Date: 24.07.2017

package com.project.core.photomodule;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.project.restservice.ApiClient;
import com.project.restservice.ServerResponse;

import java.util.Date;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Photo {

    @SerializedName("photo_id")
    @Expose
    private String photoId;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("owner_id")
    @Expose
    private String ownerId;

    @SerializedName("location_path")
    @Expose
    private String locationPath;

    @SerializedName("created_at")
    @Expose
    private Date createdAt;

    @SerializedName("updated_at")
    @Expose
    private Date updatedAt;

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getLocationPath() {
        return locationPath;
    }

    public void setLocationPath(String locationPath) {
        this.locationPath = locationPath;
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

    public static void uploadProfilePhoto(String userId, MultipartBody.Part photoPart, final Context context){
        ApiClient.imageApi().uploadSingleImage( userId, photoPart ).enqueue( new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if (response.isSuccessful()) {
                    //Toast.makeText( context, "başarılı", Toast.LENGTH_LONG ).show();
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                //Log.e( "UserTimeline", t.getMessage() );
                Toast.makeText( context, t.getMessage(), Toast.LENGTH_LONG ).show();
            }
        } );
    }
}
