// Developer: Ahmet Kaymak
// Date: 21.07.2017

package com.project.ui.patient;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.ahmetkaymak.vitae.R;
import com.alexzh.circleimageview.CircleImageView;
import com.bumptech.glide.Glide;
import com.project.core.postmodule.UserPost;
import com.project.core.patientmodule.Patient;
import com.project.core.usermodule.UserRelationship;
import com.project.restservice.ApiClient;
import com.project.restservice.serverResponse.ServerResponse;
import com.project.ui.main.timeline.adapter.ProfilePostAdapter;
import com.project.utils.Typefaces;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PatientActivity extends AppCompatActivity {

    private List<UserPost> postList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ProfilePostAdapter mAdapter;
    private View profileView;

    private TextView userName;
    private TextView userId;
    private TextView followButton;
    private RelativeLayout aboutMeRelativeLayout;
    private ImageView aboutMeIcon;
    private ImageView birthdayIcon;
    private ImageView contacts;
    private TextView noPostText;
    private TextView aboutMe;
    private TextView contactNumber;
    private TextView birthday;
    private ViewFlipper viewFlipper;
    private CircleImageView profilePicture;
    private Bitmap bitmap;
    private String visitorUserId;
    private String visitedUserId;
    private String visitedUserName;
    private String profilePhotoPath;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_patient );

        Intent myIntent = getIntent();
        visitorUserId = myIntent.getStringExtra( "visitorId" );
        visitedUserId = myIntent.getStringExtra( "visitedId" );

        userName = (TextView) findViewById( R.id.patient_profile_user_name );
        userName.setTypeface( Typefaces.getRobotoBold( getBaseContext()) );
        userId = (TextView) findViewById( R.id.patient_profile_user_id );
        userId.setTypeface( Typefaces.getRobotoLight( getBaseContext() ) );
        aboutMe = (TextView) findViewById( R.id.patient_profile_about_me );
        aboutMe = (TextView) findViewById( R.id.patient_profile_about_me );
        aboutMe.setTypeface( Typefaces.getLatoLight( getBaseContext() ) );
        profilePicture = (CircleImageView) findViewById( R.id.patient_profile_picture );
        aboutMeIcon = (ImageView) findViewById( R.id.patient_profile_about_me_icon );
        aboutMeIcon.setVisibility( View.GONE );
        birthday = (TextView) findViewById( R.id.patient_profile_birtday );
        birthday.setTypeface( Typefaces.getLatoLight( getBaseContext() ) );
        birthdayIcon = (ImageView) findViewById( R.id.patient_profile_birtday_icon );
        birthdayIcon.setVisibility( View.GONE );
        contactNumber = (TextView) findViewById( R.id.patient_profile_contact_number );
        contactNumber.setTypeface( Typefaces.getLatoRegular( getBaseContext() ) );
        contacts = (ImageView) findViewById( R.id.patient_profile_contact_icon );
        contacts.setVisibility( View.GONE );
        viewFlipper = (ViewFlipper) findViewById( R.id.view_flipper );
        aboutMeRelativeLayout = (RelativeLayout) findViewById( R.id.about_me_relative_layout );
        noPostText = (TextView) findViewById( R.id.no_post_text );

        followButton = (TextView) findViewById( R.id.patient_profile_follow_button );
        profilePhotoPath = null;

        getPatientProfileInformations( visitedUserId );
        getUserProfilePost( visitedUserId );
        areUsersConnected( visitedUserId, visitorUserId );

        followButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (followButton.getText().toString().equals( getResources().getString( R.string.follow ) )) {
                    follow( visitorUserId, visitedUserId );
                } else {
                    unfollow( visitorUserId, visitedUserId );
                }
            }
        } );

        //making back arrow on toolbar
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
        getSupportActionBar().setDisplayShowHomeEnabled( true );
        getSupportActionBar().setTitle( "" );

        toolbar.setNavigationOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        } );
    }

    private void getPatientProfileInformations(String visitedUserId) {
        try {
            ApiClient.patientApi().getPatientProfileInformation( visitedUserId ).enqueue( new Callback<Patient>() {
                @Override
                public void onResponse(Call<Patient> call, Response<Patient> response) {
                    if (response.isSuccessful()) {
                        visitedUserName = response.body().getUserName();
                        userName.setText( visitedUserName );
                        userId.setText( response.body().getUserId() );
                        aboutMe.setText( response.body().getAboutMe() );
                        if (aboutMe.getText().equals( "" )) {
                            aboutMeRelativeLayout.setVisibility( View.GONE );
                        }
                        String photoId = response.body().getProfilePictureId();
                        aboutMeIcon.setVisibility( View.VISIBLE );
                        birthdayIcon.setVisibility( View.VISIBLE );
                        contacts.setVisibility( View.VISIBLE );
                        birthday.setText( DateFormat.format( "dd.MM.yyyy", response.body().getPatients().get( 0 ).getBirthday() ).toString() );
                        contactNumber.setText( String.valueOf( response.body().getFriendCount() ) + " " + getString( R.string.contact ) );
                        if (!photoId.equals( "" )) {
                            String picturePath = "http://178.62.223.153:3000/images/" + photoId.charAt( 0 ) + "/"
                                    + photoId.charAt( 1 ) + "/" + photoId.charAt( 2 ) + "/" + photoId.charAt( 3 ) + "/" + photoId.charAt( 4 ) + "/"
                                    + photoId.charAt( 5 ) + "/" + photoId.charAt( 6 ) + "/" + photoId.charAt( 7 ) + "/" + photoId.charAt( 9 ) + "/"
                                    + photoId.charAt( 10 ) + "/" + photoId.charAt( 11 ) + "/" + photoId.charAt( 12 ) + "/" + photoId + ".jpg";

                            profilePhotoPath = picturePath;
                            Glide.with( getBaseContext() )
                                    .load( picturePath )
                                    .into( profilePicture );
                        } else {
                            Bitmap bitmap = BitmapFactory.decodeResource( getBaseContext().getResources(), R.drawable.empty_profile );
                            Bitmap circleBitmap = Bitmap.createBitmap( bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888 );
                            BitmapShader shader = new BitmapShader( bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP );
                            Paint paint = new Paint();
                            paint.setShader( shader );
                            paint.setAntiAlias( true );
                            Canvas c = new Canvas( circleBitmap );
                            c.drawCircle( bitmap.getWidth() / 2, bitmap.getHeight() / 2, bitmap.getWidth() / 2, paint );
                            profilePicture.setImageBitmap( circleBitmap );
                        }
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
                        if (postList.size() == 0) {
                            viewFlipper.setDisplayedChild( 1 );
                            noPostText.setText( visitedUserName + " " + getString( R.string.patient_have_no_post ) );
                        } else {
                            recyclerView = (RecyclerView) findViewById( R.id.patient_profile_post_recycler_view );
                            mAdapter = new ProfilePostAdapter( postList, visitorUserId, profilePhotoPath, getBaseContext() );
                            recyclerView.setHasFixedSize( true );
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager( getBaseContext() );
                            recyclerView.setLayoutManager( mLayoutManager );
                            recyclerView.setItemAnimator( new DefaultItemAnimator() );
                            recyclerView.setAdapter( mAdapter );
                            mAdapter.notifyDataSetChanged();
                        }
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