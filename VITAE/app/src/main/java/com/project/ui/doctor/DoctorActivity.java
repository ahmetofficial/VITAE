// Developer: Ahmet Kaymak
// Date: 21.07.2017

package com.project.ui.doctor;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
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
import com.project.core.doctormodule.Doctor;
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

public class DoctorActivity extends AppCompatActivity {

    private List<UserPost> postList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ProfilePostAdapter mAdapter;
    private View profileView;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView userName;
    private TextView userId;
    private TextView followButton;
    private TextView hospital;
    private TextView clinic;
    private RelativeLayout aboutMeRelativeLayout;
    private ImageView verificationBadge;
    private ImageView aboutMeIcon;
    private ImageView birthdayIcon;
    private ImageView contacts;
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

    private FragmentDoctorProfilePosts fragmentDoctorProfilePosts;
    private FragmentDoctorProfileReviews fragmentDoctorProfileReviews;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_doctor );

        Intent myIntent = getIntent();
        visitorUserId = myIntent.getStringExtra( "visitorId" );
        visitedUserId = myIntent.getStringExtra( "visitedId" );

        tabLayout = (TabLayout) findViewById( R.id.tabs );
        viewPager = (ViewPager) findViewById( R.id.viewpager );
        userName = (TextView) findViewById( R.id.doctor_profile_user_name );
        userName.setTypeface( Typefaces.getRobotoBold( getBaseContext()) );
        userId = (TextView) findViewById( R.id.doctor_profile_user_id );
        userId.setTypeface( Typefaces.getRobotoLight( getBaseContext() ) );
        hospital = (TextView) findViewById( R.id.doctor_profile_hospital_name );
        hospital.setTypeface( Typefaces.getLatoLight( getBaseContext() ) );
        clinic = (TextView) findViewById( R.id.doctor_profile_clinic_name );
        clinic.setTypeface( Typefaces.getLatoLight( getBaseContext() ) );
        verificationBadge = (ImageView) findViewById( R.id.doctor_profile_verification_badge );
        aboutMe = (TextView) findViewById( R.id.doctor_profile_about_me );
        aboutMe.setTypeface( Typefaces.getLatoLight( getBaseContext() ) );
        profilePicture = (CircleImageView) findViewById( R.id.doctor_profile_picture );
        aboutMeIcon = (ImageView) findViewById( R.id.doctor_profile_about_me_icon );
        aboutMeIcon.setVisibility( View.GONE );
        birthday = (TextView) findViewById( R.id.doctor_profile_birtday );
        birthday.setTypeface( Typefaces.getLatoLight( getBaseContext() ) );
        birthdayIcon = (ImageView) findViewById( R.id.doctor_profile_birtday_icon );
        birthdayIcon.setVisibility( View.GONE );
        contactNumber = (TextView) findViewById( R.id.doctor_profile_contact_number );
        contactNumber.setTypeface( Typefaces.getLatoRegular( getBaseContext() ) );
        contacts = (ImageView) findViewById( R.id.doctor_profile_contact_icon );
        contacts.setVisibility( View.GONE );
        viewFlipper = (ViewFlipper) findViewById( R.id.view_flipper );
        aboutMeRelativeLayout = (RelativeLayout) findViewById( R.id.about_me_relative_layout );

        followButton = (TextView) findViewById( R.id.doctor_profile_follow_button );
        profilePhotoPath = "";
        verificationBadge.setVisibility( View.GONE );

        getDoctorProfileInformations( visitedUserId );
        setupViewPager( viewPager );
        tabLayout.setupWithViewPager( viewPager );
        setupTabIcons();

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

    private void getDoctorProfileInformations(String visitedUserId) {
        try {
            ApiClient.doctorApi().getDoctorProfileInformation( visitedUserId ).enqueue( new Callback<Doctor>() {
                @Override
                public void onResponse(Call<Doctor> call, Response<Doctor> response) {
                    if (response.isSuccessful()) {
                        if(response.body().getDoctors().get( 0 ).getIsVerified()==1){
                            verificationBadge.setVisibility( View.VISIBLE );
                        }else{
                            verificationBadge.setImageDrawable( getDrawable(R.drawable.ic_alert_circle_outline_white_24dp ));
                            verificationBadge.setVisibility( View.VISIBLE );
                        }
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
                        hospital.setText( response.body().getDoctorHaveHospitals().get( 0 ).getHospital().getHospitalName() );
                        clinic.setText( response.body().getDoctorHaveHospitals().get( 0 ).getClinic().getClinicName() );
                        birthday.setText( DateFormat.format( "dd.MM.yyyy", response.body().getDoctors().get( 0 ).getBirthday() ).toString() );
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
                public void onFailure(Call<Doctor> call, Throwable t) {
                    Log.e( "UserTimeline", t.getMessage() );
                    Toast.makeText( getBaseContext(), t.getMessage(), Toast.LENGTH_LONG ).show();
                }
            } );
        } catch (Exception e) {
            Log.e( "UserTimeline", e.getMessage() );
            Toast.makeText( getBaseContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
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

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter( getSupportFragmentManager() );
        fragmentDoctorProfilePosts = new FragmentDoctorProfilePosts(visitorUserId, visitedUserId, profilePhotoPath );
        fragmentDoctorProfileReviews = new FragmentDoctorProfileReviews( visitorUserId, visitedUserId,visitedUserName );
        adapter.addFragment( fragmentDoctorProfilePosts, getString( R.string.posts ) );
        adapter.addFragment( fragmentDoctorProfileReviews, getString( R.string.reviews ));
        viewPager.setAdapter( adapter );
    }

    private void setupTabIcons() {
        int[] tabIcons = {
                R.drawable.icon_timeline_white,
                R.drawable.ic_bulletin_board_white_24dp
        };

        tabLayout.getTabAt( 0 ).setIcon( tabIcons[0] );
        tabLayout.getTabAt( 1 ).setIcon( tabIcons[1] );
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }
    }
}