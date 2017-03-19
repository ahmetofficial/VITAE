// Developer: Ahmet Kaymak
// Date: 22.02.2016

package com.project.uimodule.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lavie.users.R;
import com.project.restservice.ApiClient;
import com.project.restservice.ServerResponse;
import com.project.uimodule.BaseActivity;
import com.project.uimodule.main.MenuActivity;
import com.project.uimodule.signup.SignUpActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {

    Button btn_login, btn_signUp;
    EditText txt_userId, txt_password;
    boolean authentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

        btn_login = (Button) findViewById( R.id.btn_signIn );
        btn_signUp = (Button) findViewById( R.id.btn_signIn_create_account );
        txt_userId = (EditText) findViewById( R.id.txt_signIn_username );
        txt_password = (EditText) findViewById( R.id.txt_signIn_password );

        btn_login.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authentication = false;
                loginUser( txt_userId.getText().toString(), txt_password.getText().toString() );
            }

        } );

        btn_signUp.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( LoginActivity.this, SignUpActivity.class ) );
            }
        } );

    }

    private void loginUser(final String user_id, String password) {
        try {
            ApiClient.userApi().authenticateLogin( new UserLoginPost( user_id, password ) )
                    .enqueue( new Callback<ServerResponse>() {
                        @Override
                        public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                            if (response.isSuccessful()) {
                                if (!response.body().getStatus().equals( null )) {
                                    Toast.makeText( LoginActivity.this, "user login" + " " + response.body().getStatus(), Toast.LENGTH_SHORT ).show();
                                    if (response.body().getStatus().equals( "true" )) {
                                        MenuActivity.userId=user_id;
                                        startActivity( new Intent( LoginActivity.this, MenuActivity.class ) );
                                    }
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ServerResponse> call, Throwable t) {
                            Log.e( "MenuActivity", t.getMessage() );
                        }
                    } );


        } catch (Exception e) {
            Log.e( "MenuActivity", e.getMessage() );

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


