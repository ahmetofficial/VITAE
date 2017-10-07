// Developer: Ahmet Kaymak
// Date: 27.02.2016

package com.project.ui.main.profile;

import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmetkaymak.vitae.R;
import com.bumptech.glide.Glide;
import com.project.core.postmodule.UserPost;
import com.project.core.usermodule.Patient;
import com.project.restservice.ApiClient;
import com.project.ui.main.timeline.adapter.ProfilePostAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentProfile extends Fragment {

    private ArrayList<UserPost> postList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ProfilePostAdapter mAdapter;
    private View profileView;
    private String profilePhotoPath;

    private TextView userName;
    private TextView userId;
    private TextView aboutMe;
    private ImageView profile_picture;
    private Bitmap bitmap;
    private String userID;

    public FragmentProfile(String userID) {
        this.userID = userID;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Typeface typeLight = Typeface.createFromAsset( getActivity().getAssets(), "fonts/Roboto-Light.ttf" );

        profileView = inflater.inflate( R.layout.fragment_profile, container, false );
        userName = (TextView) profileView.findViewById( R.id.fragment_profile_txtUserName );
        userName.setTypeface( typeLight );
        userId = (TextView) profileView.findViewById( R.id.fragment_profile_txtUserId );
        userId.setTypeface( typeLight );
        aboutMe = (TextView) profileView.findViewById( R.id.fragment_profile_txtAboutMe );
        aboutMe.setTypeface( typeLight );
        profile_picture = (ImageView) profileView.findViewById( R.id.fragment_profile_circle_image );

        getPatientProfileInformations();
        getUserProfilePost();

        return profileView;
    }

    private void getPatientProfileInformations() {
        try {
            ApiClient.patientApi().getPatientProfileInformation( userID ).enqueue( new Callback<Patient>() {
                @Override
                public void onResponse(Call<Patient> call, Response<Patient> response) {
                    if (response.isSuccessful()) {
                        userName.setText( response.body().getUserName() );
                        userId.setText( response.body().getUserId() );
                        aboutMe.setText( response.body().getAboutMe() );
                        String photoId = response.body().getProfilePictureId();

                        if(!photoId.equals( "" )) {
                            String picturePath = "http://178.62.223.153:3000/images/" + photoId.charAt( 0 ) + "/"
                                    + photoId.charAt( 1 ) + "/" + photoId.charAt( 2 ) + "/" + photoId.charAt( 3 ) + "/" + photoId.charAt( 4 ) + "/"
                                    + photoId.charAt( 5 ) + "/" + photoId.charAt( 6 ) + "/" + photoId.charAt( 7 ) + "/" + photoId.charAt( 9 ) + "/"
                                    + photoId.charAt( 10 ) + "/" + photoId.charAt( 11 ) + "/" + photoId.charAt( 12 ) + "/" + photoId + ".jpg";

                            profilePhotoPath= picturePath;
                            Glide.with( getContext() )
                                    .load( picturePath )
                                    .into( profile_picture );
                        }
                    }
                }

                @Override
                public void onFailure(Call<Patient> call, Throwable t) {
                    Log.e( "UserTimeline", t.getMessage() );
                    Toast.makeText( getActivity(), t.getMessage(), Toast.LENGTH_LONG ).show();
                }
            } );
        } catch (Exception e) {
            Log.e( "UserTimeline", e.getMessage() );
            Toast.makeText( this.getContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }

    private void getUserProfilePost() {
        try {
            ApiClient.postApi().getUserPostsById( userID ).enqueue( new Callback<UserPost>() {
                @Override
                public void onResponse(Call<UserPost> call, Response<UserPost> response) {
                    if (response.isSuccessful()) {
                        postList = (ArrayList) response.body().getPosts();
                        recyclerView = (RecyclerView) profileView.findViewById( R.id.fragment_profile_recycler_view );
                        mAdapter = new ProfilePostAdapter( postList, userID,profilePhotoPath, getContext() );
                        recyclerView.setHasFixedSize( true );
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager( getActivity() );
                        recyclerView.setLayoutManager( mLayoutManager );
                        recyclerView.setItemAnimator( new DefaultItemAnimator() );
                        recyclerView.setAdapter( mAdapter );
                        mAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<UserPost> call, Throwable t) {
                    Log.e( "UserTimeline", t.getMessage() );
                    Toast.makeText( getActivity(), t.getMessage(), Toast.LENGTH_LONG ).show();
                }
            } );
        } catch (Exception e) {
            Log.e( "UserTimeline", e.getMessage() );
            Toast.makeText( this.getContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }
}
