// Developer: Ahmet Kaymak
// Date: 27.02.2016

package com.project.ui.main.profile;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.ahmetkaymak.vitae.R;
import com.bumptech.glide.Glide;
import com.project.core.postmodule.UserPost;
import com.project.core.usermodule.Patient;
import com.project.restservice.ApiClient;
import com.project.ui.main.timeline.adapter.ProfilePostAdapter;
import com.project.utils.Typefaces;

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
    private TextView userIdText;
    private ImageView profilePicture;
    private RelativeLayout aboutMeRelativeLayout;
    private ImageView aboutMeIcon;
    private ImageView birthdayIcon;
    private ImageView contacts;
    private TextView aboutMe;
    private TextView contactNumber;
    private TextView birthday;
    private ViewFlipper viewFlipper;
    private Bitmap bitmap;
    private String userId;

    public FragmentProfile(String userId) {
        this.userId = userId;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        profileView = inflater.inflate( R.layout.fragment_profile, container, false );
        userName = (TextView) profileView.findViewById( R.id.patient_profile_user_name );
        userName.setTypeface( Typefaces.getRobotoBold( getContext() ) );
        userIdText = (TextView) profileView.findViewById( R.id.patient_profile_user_id );
        userIdText.setTypeface( Typefaces.getRobotoLight( getContext() ) );
        aboutMe = (TextView) profileView.findViewById( R.id.patient_profile_about_me );
        aboutMe.setTypeface( Typefaces.getLatoLight( getContext() ) );
        profilePicture = (ImageView) profileView.findViewById( R.id.patient_profile_picture );
        aboutMeIcon = (ImageView) profileView.findViewById( R.id.patient_profile_about_me_icon );
        aboutMeIcon.setVisibility( View.GONE );
        birthday = (TextView) profileView.findViewById( R.id.patient_profile_birtday );
        birthday.setTypeface( Typefaces.getLatoLight( getContext() ) );
        birthdayIcon = (ImageView) profileView.findViewById( R.id.patient_profile_birtday_icon );
        birthdayIcon.setVisibility( View.GONE );
        contactNumber = (TextView) profileView.findViewById( R.id.patient_profile_contact_number );
        contactNumber.setTypeface( Typefaces.getLatoRegular( getContext() ) );
        contacts = (ImageView) profileView.findViewById( R.id.patient_profile_contact_icon );
        contacts.setVisibility( View.GONE );
        viewFlipper = (ViewFlipper) profileView.findViewById( R.id.view_flipper );
        aboutMeRelativeLayout = (RelativeLayout) profileView.findViewById( R.id.about_me_relative_layout );


        getPatientProfileInformations();
        getUserProfilePost();

        return profileView;
    }

    private void getPatientProfileInformations() {
        try {
            ApiClient.patientApi().getPatientProfileInformation( userId ).enqueue( new Callback<Patient>() {
                @Override
                public void onResponse(Call<Patient> call, Response<Patient> response) {
                    if (response.isSuccessful()) {
                        userName.setText( response.body().getUserName() );
                        userIdText.setText( response.body().getUserId() );
                        aboutMe.setText( response.body().getAboutMe() );
                        if(aboutMe.getText().equals( "" )){
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
                            Glide.with( getContext() )
                                    .load( picturePath )
                                    .into( profilePicture );
                        } else {
                            Bitmap bitmap = BitmapFactory.decodeResource( getContext().getResources(), R.drawable.empty_profile );
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
            ApiClient.postApi().getUserPostsById( userId ).enqueue( new Callback<UserPost>() {
                @Override
                public void onResponse(Call<UserPost> call, Response<UserPost> response) {
                    if (response.isSuccessful()) {
                        postList = (ArrayList) response.body().getPosts();
                        if (postList.size() == 0) {
                            viewFlipper.setDisplayedChild( 1 );
                        } else {
                            recyclerView = (RecyclerView) profileView.findViewById( R.id.patient_profile_post_recycler_view );
                            mAdapter = new ProfilePostAdapter( postList, userId, profilePhotoPath, getContext() );
                            recyclerView.setHasFixedSize( true );
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager( getActivity() );
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
                    Toast.makeText( getActivity(), t.getMessage(), Toast.LENGTH_LONG ).show();
                }
            } );
        } catch (Exception e) {
            Log.e( "UserTimeline", e.getMessage() );
            Toast.makeText( this.getContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }

}
