// Developer: Ahmet Kaymak
// Date: 17.02.2016

package com.project.restservice.imagemodule;

import com.project.restservice.ServerResponse;
import com.project.restservice.ServerResponseWithPhotoId;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiImage {

    @Multipart
    @POST("imageModule/uploadProfilePicture/{user_id}")
    Call<ServerResponse> uploadUserProfilePicture(@Path("user_id") String userId, @Part MultipartBody.Part photo);

    @Multipart
    @POST("imageModule/uploadUserPostPhoto/{user_id}")
    Call<ServerResponseWithPhotoId> uploadUserPostPhoto(@Path("user_id") String userId, @Part MultipartBody.Part photo);
}
