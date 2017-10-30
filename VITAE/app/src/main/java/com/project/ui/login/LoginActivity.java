// Developer: Ahmet Kaymak
// Date: 22.02.2016

package com.project.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ahmetkaymak.vitae.R;
import com.project.restservice.ApiClient;
import com.project.restservice.serverResponse.ServerResponse;
import com.project.ui.BaseActivity;
import com.project.ui.main.MenuActivity;
import com.project.ui.signup.SignUpMainActivity;
import com.project.utils.SessionUtils;
import com.project.utils.Typefaces;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {

    private Button btn_login, btn_signUp;
    private EditText txt_userId, txt_password;
    private UserSessionManager session;
    private  LoginAuthenticationFragment loginAuthenticationFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

        session = new UserSessionManager( getApplicationContext() );
        if (session.isUserLoggedIn()) {
            HashMap<String, String> user = session.getUserDetails();
            String userId = user.get( UserSessionManager.KEY_USERNAME );
            String userTypeId = user.get( UserSessionManager.KEY_USERTYPEID );
            SessionUtils.setUserId( userId );
            Intent intent = new Intent( getBaseContext(), MenuActivity.class );
            MenuActivity.userId=userId;
            intent.putExtra( "userId", userId );
            intent.putExtra( "userTypeId", Integer.valueOf(userTypeId) );
            intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
            startActivity( intent );
        } else {
            btn_login = (Button) findViewById( R.id.btn_signIn );
            btn_signUp = (Button) findViewById( R.id.btn_signIn_create_account );
            txt_userId = (EditText) findViewById( R.id.txt_signIn_username );
            txt_userId.setTypeface( Typefaces.getRobotoLight( getBaseContext() ) );
            txt_password = (EditText) findViewById( R.id.txt_signIn_password );
            txt_password.setTypeface( Typefaces.getRobotoLight( getBaseContext() ) );

            btn_login.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fm = getSupportFragmentManager();
                    loginAuthenticationFragment=new LoginAuthenticationFragment();
                    loginAuthenticationFragment.show( fm, "Loading" );
                    loginUser( txt_userId.getText().toString().trim(), txt_password.getText().toString().trim() );
                }
            } );

            btn_signUp.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity( new Intent( LoginActivity.this, SignUpMainActivity.class ) );
                }
            } );
        }
    }

    private void loginUser(final String userId, final String password) {
        try {
            ApiClient.userApi().authenticateLogin( new UserLoginPost( userId, password ) )
                    .enqueue( new Callback<ServerResponse>() {
                        @Override
                        public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                            if (response.isSuccessful()) {
                                if (response.body().getStatus().equals( "true" )) {
                                    loginAuthenticationFragment.dismiss();
                                    Toast.makeText( LoginActivity.this, getString( R.string.login_succesfull ), Toast.LENGTH_SHORT ).show();
                                    if (response.body().getStatus().equals( "true" )) {
                                        session.createUserLoginSession( userId,String.valueOf( response.body().getUserTypeId() ),password );
                                        Intent intent = new Intent( getBaseContext(), MenuActivity.class );
                                        MenuActivity.userId=userId;
                                        intent.putExtra( "userId", userId );
                                        intent.putExtra( "userTypeId", Integer.valueOf(response.body().getUserTypeId()) );
                                        intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
                                        startActivity( intent );
                                    }
                                }else{
                                    loginAuthenticationFragment.dismiss();
                                    txt_userId.setText( null );
                                    txt_password.setText( null );
                                    Toast.makeText( LoginActivity.this, getString( R.string.login_unsuccesfull ), Toast.LENGTH_SHORT ).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ServerResponse> call, Throwable t) {
                            Toast.makeText( LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT ).show();
                        }
                    } );
        } catch (Exception e) {
            Toast.makeText( LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT ).show();
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