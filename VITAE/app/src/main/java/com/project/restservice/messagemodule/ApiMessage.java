// Developer: Ahmet Kaymak
// Date: 17.02.2016

package com.project.restservice.messagemodule;

import com.project.core.messagemodule.Message;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiMessage {

    @POST("messageModule/message/getMessages")
    Call<Message> getMessages(@Body Message message);
}
