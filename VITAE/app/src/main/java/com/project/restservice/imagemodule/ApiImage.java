// Developer: Ahmet Kaymak
// Date: 17.02.2016

package com.project.restservice.imagemodule;

import com.project.restservice.ServerResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiImage {

    @Multipart
    @POST("imageModule/uploadProfilePicture/{userId}")
    Call<ServerResponse> uploadSingleImage(@Path("userId") String userId, @Part MultipartBody.Part photo);
}
