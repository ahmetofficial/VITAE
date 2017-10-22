// Developer: Ahmet Kaymak
// Date: 17.02.2016

package com.project.restservice.messagemodule;

import com.project.core.messagemodule.Conversation;
import com.project.restservice.serverresponse.ServerResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiConversation {

    @GET("/messageModule/conversation/getConversations/{userId}")
    Call<Conversation> getUserConversations(@Path("userId") String userId);

    @POST("/messageModule/conversation/createConversation")
    Call<ServerResponse> createConversation(@Body Conversation conversation);
}
