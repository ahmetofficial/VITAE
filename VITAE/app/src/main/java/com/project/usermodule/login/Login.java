package com.project.usermodule.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lavie.users.R;
import com.project.BaseActivity;
import com.project.restservice.ApiClient;
import com.project.restservice.user.ApiUserInterface;
import com.project.restservice.user.LoginResponse;
import com.project.usermodule.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends BaseActivity{

    User logUser=new User();
    Button btn_login,btn_loginWithFacebook;
    EditText txt_userId,txt_password;
    boolean authentication;
    int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);


        btn_login = (Button)findViewById(R.id.btn_login);
        txt_userId = (EditText)findViewById(R.id.txt_username);
        txt_password = (EditText)findViewById(R.id.txt_password);
        //btn_loginWithFacebook = (Button)findViewById(R.id.btn_loginWithFacebook);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authentication=false;
                ApiUserInterface apiService = ApiClient.getClient().create(ApiUserInterface.class);
                Call<LoginResponse> call = apiService.authenticateLogin(txt_userId.getText().toString(),txt_password.getText().toString());
                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse>call, Response<LoginResponse> response) {
                        authentication = (boolean)response.body().getStatus();
                    }

                    @Override
                    public void onFailure(Call<LoginResponse>call, Throwable t) {
                        //in the case of error
                    }
                });
                if(authentication==true){
                    System.out.println("yuppi");
                }else{

                }
            }

        });

        /*
        btn_loginWithFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        */
    }

}


