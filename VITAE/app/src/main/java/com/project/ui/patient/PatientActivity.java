// Developer: Ahmet Kaymak
// Date: 21.07.2017

package com.project.ui.patient;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmetkaymak.vitae.R;
import com.bumptech.glide.Glide;
import com.project.core.postmodule.UserPost;
import com.project.core.usermodule.Patient;
import com.project.core.usermodule.UserRelationship;
import com.project.restservice.ApiClient;
import com.project.restservice.ServerResponse;
import com.project.ui.main.timeline.adapter.PostAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PatientActivity extends AppCompatActivity {

    private List<UserPost> postList = new ArrayList<>();
    private RecyclerView recyclerView;
    private PostAdapter mAdapter;
    private View profileView;

    private TextView userName;
    private TextView userId;
    private TextView aboutMe;
    private TextView followButton;
    private ImageView profile_picture;
    private Bitmap bitmap;
    private String visitorUserId;
    private String visitedUserId;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_patient );

        Intent myIntent = getIntent();
        visitorUserId = myIntent.getStringExtra( "visitorId" );
        visitedUserId = myIntent.getStringExtra( "visitedId" );

        userName = (TextView) findViewById( R.id.activity_patient_txtUserName );
        userId = (TextView) findViewById( R.id.activity_patient_txtUserId );
        aboutMe = (TextView) findViewById( R.id.activity_patient_txtAboutMe );
        profile_picture = (ImageView) findViewById( R.id.activity_patient_circle_image );
        followButton = (TextView) findViewById( R.id.activity_patient_follow_button );

        getPatientProfileInformations( visitedUserId );
        getUserProfilePost( visitedUserId );
        areUsersConnected( visitedUserId, visitorUserId );

        followButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(followButton.getText().toString().equals( getResources().getString( R.string.follow ) )){
                    follow( visitorUserId,visitedUserId );
                }else{
                    unfollow( visitorUserId,visitedUserId );
                }
            }
        });
    }

    private void getPatientProfileInformations(String visitedUserId) {
        try {
            ApiClient.patientApi().getPatientProfileInformation( visitedUserId ).enqueue( new Callback<Patient>() {
                @Override
                public void onResponse(Call<Patient> call, Response<Patient> response) {
                    if (response.isSuccessful()) {
                        userName.setText( response.body().getUserName() );
                        userId.setText( response.body().getUserId() );
                        aboutMe.setText( response.body().getAboutMe() );
                        Glide.with(getBaseContext()).load("http://razemovafaghiat.ir/uploads/download/egve.jpg").into(profile_picture);
                        /*

                        */
                    }
                }

                @Override
                public void onFailure(Call<Patient> call, Throwable t) {
                    Log.e( "UserTimeline", t.getMessage() );
                    Toast.makeText( getBaseContext(), t.getMessage(), Toast.LENGTH_LONG ).show();
                }
            } );
        } catch (Exception e) {
            Log.e( "UserTimeline", e.getMessage() );
            Toast.makeText( getBaseContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }

    private void getUserProfilePost(final String visitedUserId) {
        try {
            ApiClient.postApi().getUserPostsById( visitedUserId ).enqueue( new Callback<UserPost>() {
                @Override
                public void onResponse(Call<UserPost> call, Response<UserPost> response) {
                    if (response.isSuccessful()) {
                        postList = (ArrayList) response.body().getPosts();
                        recyclerView = (RecyclerView) findViewById( R.id.activity_patient_recycler_view );
                        mAdapter = new PostAdapter( postList,visitorUserId,getBaseContext() );
                        recyclerView.setHasFixedSize( true );
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager( getBaseContext() );
                        recyclerView.setLayoutManager( mLayoutManager );
                        recyclerView.setItemAnimator( new DefaultItemAnimator() );
                        recyclerView.setAdapter( mAdapter );
                        mAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<UserPost> call, Throwable t) {
                    Log.e( "UserTimeline", t.getMessage() );
                    Toast.makeText( getBaseContext(), t.getMessage(), Toast.LENGTH_LONG ).show();
                }
            } );
        } catch (Exception e) {
            Log.e( "UserTimeline", e.getMessage() );
            Toast.makeText( this.getBaseContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }

    private void areUsersConnected(String visitedUserId, String visitorUserId) {
        UserRelationship userRelationship = new UserRelationship();
        userRelationship.setActiveUserId( visitorUserId );
        userRelationship.setPassiveUserId( visitedUserId );

        try {
            ApiClient.userApi().areUsersConnected( userRelationship ).enqueue( new Callback<UserRelationship>() {
                @Override
                public void onResponse(Call<UserRelationship> call, Response<UserRelationship> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getRelationship().size() == 0) {
                            followButton.setText( getString( R.string.follow ) );
                        } else {
                            followButton.setText( getString( R.string.unfollow ) );
                        }
                    }
                }

                @Override
                public void onFailure(Call<UserRelationship> call, Throwable t) {
                    Log.e( "UserTimeline", t.getMessage() );
                    Toast.makeText( getBaseContext(), t.getMessage(), Toast.LENGTH_LONG ).show();
                }
            } );
        } catch (Exception e) {
            Log.e( "UserTimeline", e.getMessage() );
            Toast.makeText( this.getBaseContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }

    private void follow(String visitorUserId, String visitedUserId) {
        UserRelationship userRelationship = new UserRelationship();
        userRelationship.setActiveUserId( visitorUserId );
        userRelationship.setPassiveUserId( visitedUserId );

        try {
            ApiClient.userApi().follow( userRelationship ).enqueue( new Callback<ServerResponse>() {
                @Override
                public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equals( "true" )) {
                            followButton.setText( getString( R.string.unfollow ) );
                        } else {
                            Toast.makeText( getBaseContext(), getResources().getString( R.string.followIsNotSuccesfull ), Toast.LENGTH_LONG ).show();
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
            Toast.makeText( this.getBaseContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }

    private void unfollow(String visitorUserId, String visitedUserId) {
        UserRelationship userRelationship = new UserRelationship();
        userRelationship.setActiveUserId( visitorUserId );
        userRelationship.setPassiveUserId( visitedUserId );

        try {
            ApiClient.userApi().unfollow( userRelationship ).enqueue( new Callback<ServerResponse>() {
                @Override
                public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equals( "true" )) {
                            followButton.setText( getString( R.string.follow ) );
                        } else {
                            Toast.makeText( getBaseContext(), getResources().getString( R.string.unfollowIsNotSuccesfull ), Toast.LENGTH_LONG ).show();
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
            Toast.makeText( this.getBaseContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }
}