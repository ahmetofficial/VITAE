package com.project.usermodule.login;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                loginUser(txt_userId.getText().toString(),txt_password.getText().toString());

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

    private void loginUser(String user_id, String password){
        try{
            ApiClient.getMyApi().authenticateLogin(new UserLoginPost(user_id,password))
                    .enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            if (response.isSuccessful()) {
                                if (!response.body().getStatus().equals(null)) {
                                    Toast.makeTgit ext(Login.this, "user login" + response.body().getStatus() + "", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            Log.e("MainActivity", t.getMessage());
                        }
                    });


        }catch (Exception e){
            Log.e("MainActivity", e.getMessage());

        }
    }
    public class UserLoginPost{
        private String user_id;
        private String password;

        public UserLoginPost(String user_id, String password) {
            this.user_id = user_id;
            this.password = password;
        }
    }
}


