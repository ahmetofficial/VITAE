// Developer: Ahmet Kaymak
// Date: 03.04.2017

package com.project.uimodule.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lavie.users.R;
import com.project.postmodule.UserPost;
import com.project.restservice.ApiClient;
import com.project.restservice.ServerResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostActivity extends AppCompatActivity {

    private EditText txt_post_text;
    private Button btn_send_post;
    public static String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate( savedInstanceState );
            setContentView( R.layout.activity_post );

            txt_post_text = (EditText) findViewById( R.id.activity_post_txt_post );
            btn_send_post = (Button) findViewById( R.id.activity_post_btn_post );

            btn_send_post.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UserPost newPost = new UserPost();
                    newPost.setUser_id( userId );
                    newPost.setPost_text( txt_post_text.getText().toString() );
                    newPost.setUrl( null );
                    createUserPost( userId, newPost );
                }

            } );
        } catch (Exception e) {
            Log.e( "UserTimeline", e.getMessage() );
            Toast.makeText( getBaseContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }

    private void createUserPost(String userId, UserPost userPost) {
        try {
            ApiClient.postApi().createNewPost( userId, userPost ).enqueue( new Callback<ServerResponse>() {
                @Override
                public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equals( "true" )) {
                            startActivity( new Intent( PostActivity.this, MenuActivity.class ) );
                        }
                    }
                }

                @Override
                public void onFailure(Call<ServerResponse> call, Throwable t) {
                    Log.e( "UserTimeline", t.getMessage() );
                    Toast.makeText( getBaseContext(), t.getMessage(), Toast.LENGTH_LONG ).show();
                }
            } );
        } catch (Exception e) {
            Log.e( "UserTimeline", e.getMessage() );
            Toast.makeText( getBaseContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }
}
