// Developer: Ahmet Kaymak
// Date: 22.02.2016

package com.project.usermodule.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lavie.users.R;
import com.project.restservice.ApiClient;
import com.project.restservice.usermodule.LoginResponse;
import com.project.uimodule.BaseActivity;
import com.project.uimodule.MenuActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {

    Button btn_login, btn_loginWithFacebook;
    EditText txt_userId, txt_password;
    boolean authentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_login = (Button) findViewById(R.id.btn_login);
        txt_userId = (EditText) findViewById(R.id.txt_username);
        txt_password = (EditText) findViewById(R.id.txt_password);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authentication = false;
                loginUser(txt_userId.getText().toString(), txt_password.getText().toString());
            }

        });

    }

    private void loginUser(String user_id, String password) {
        try {
            ApiClient.userApi().authenticateLogin(new UserLoginPost(user_id, password))
                    .enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            if (response.isSuccessful()) {
                                if (!response.body().getStatus().equals(null)) {
                                    Toast.makeText(LoginActivity.this, "user login" + response.body().getStatus() + "", Toast.LENGTH_SHORT).show();
                                    if (response.body().getStatus().equals("true")) {
                                        startActivity(new Intent(LoginActivity.this, MenuActivity.class));
                                    }
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            Log.e("MenuActivity", t.getMessage());
                        }
                    });


        } catch (Exception e) {
            Log.e("MenuActivity", e.getMessage());

        }
    }

    public class UserLoginPost {
        private String user_id;
        private String password;

        public UserLoginPost(String user_id, String password) {
            this.user_id = user_id;
            this.password = password;
        }
    }
}


