// Developer: Ahmet Kaymak
// Date: 27.02.2016

package com.project.ui.doctor;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.ahmetkaymak.vitae.R;
import com.project.core.postmodule.UserPost;
import com.project.restservice.ApiClient;
import com.project.ui.main.timeline.adapter.ProfilePostAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentDoctorProfilePosts extends Fragment {

    private ArrayList<UserPost> postList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ProfilePostAdapter mAdapter;
    private View profileView;

    private ViewFlipper viewFlipper;
    private TextView noPostText;
    private Bitmap bitmap;
    private String visitorUserId;
    private String visitedUserId;
    private String profilePhotoPath;

    public FragmentDoctorProfilePosts(String visitorUserId,String visitedUserId,String profilePhotoPath) {
        this.visitorUserId = visitorUserId;
        this.visitedUserId = visitedUserId;
        this.profilePhotoPath = profilePhotoPath;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        profileView = inflater.inflate( R.layout.fragment_user_profile_post, container, false );
        viewFlipper = (ViewFlipper) profileView.findViewById( R.id.view_flipper );
        noPostText = (TextView) profileView.findViewById( R.id.user_have_no_post );
        recyclerView = (RecyclerView) profileView.findViewById( R.id.user_profile_post_recycler_view );
        getUserProfilePost(visitorUserId,visitedUserId);

        return profileView;
    }

    private void getUserProfilePost(final String visitorUserId,String visitedUserId) {
        try {
            ApiClient.postApi().getUserPostsById( visitedUserId ).enqueue( new Callback<UserPost>() {
                @Override
                public void onResponse(Call<UserPost> call, Response<UserPost> response) {
                    if (response.isSuccessful()) {
                        postList = (ArrayList) response.body().getPosts();
                        if (postList.size() == 0) {
                            viewFlipper.setDisplayedChild( 1 );
                            noPostText.setText(getString( R.string.user_have_no_post ) );
                        } else {

                            mAdapter = new ProfilePostAdapter( postList, visitorUserId, profilePhotoPath, getContext() );
                            recyclerView.setHasFixedSize( true );
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager( getContext() );
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
                    Toast.makeText( getContext(), t.getMessage(), Toast.LENGTH_LONG ).show();
                }
            } );
        } catch (Exception e) {
            Log.e( "UserTimeline", e.getMessage() );
            Toast.makeText( this.getContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }

}
