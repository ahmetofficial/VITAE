// Developer: Ahmet Kaymak
// Date: 14.03.2017

package com.project.ui.hospitalpage;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.ahmetkaymak.vitae.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.project.core.hospitalmodule.Hospital;
import com.project.core.hospitalmodule.UserHospitalRate;
import com.project.restservice.ApiClient;
import com.project.ui.hospitalpage.adapter.UserHospitalRateAdapter;
import com.project.utils.Typefaces;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HospitalProfileActivity extends AppCompatActivity implements OnMapReadyCallback {

    private ImageView profilePicture;
    private Bitmap bitmap;
    private TextView hospitalNameText, hospitalType, hospitalNoCommentText, patientReview;
    private MaterialRatingBar hospitalRatingBar;
    private FloatingActionButton hospitalMail, hospitalPhone;
    private ViewFlipper viewFlipper;
    private ImageView addFirstComment;
    private String hospitalTelephone;
    private String hospitalEmail;
    private String hospitalAdress;
    private FragmentAddHospitalReview hospitalReview;
    private RelativeLayout ratingLayout;

    //////////////////////////////////Hospital Rates Fields/////////////////////////////////////////
    private int hospitalId;
    private String userId;
    private String hospitalName;
    private GoogleMap mMap;

    private UserHospitalRateAdapter mAdapter;
    private RecyclerView recyclerView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_hospital );

        Intent myIntent = getIntent();
        userId = myIntent.getStringExtra( "userId" );
        hospitalId = myIntent.getIntExtra( "hospitalId", 0 );

        hospitalNameText = (TextView) findViewById( R.id.hospital_activity_hospital_name );
        hospitalNameText.setGravity( Gravity.CENTER_VERTICAL );
        hospitalType = (TextView) findViewById( R.id.hospital_activity_hospital_type );
        hospitalType.setGravity( Gravity.CENTER_VERTICAL );
        hospitalNoCommentText = (TextView) findViewById( R.id.hospital_activity_no_comment_text );
        addFirstComment = (ImageView) findViewById( R.id.hospital_activity_add_first_comment_image );
        viewFlipper = (ViewFlipper) findViewById( R.id.hospital_activity_viewflipper );
        ratingLayout = (RelativeLayout) findViewById( R.id.rating_relative_layout );
        recyclerView = (RecyclerView) findViewById( R.id.recycler_view );
        patientReview = (TextView) findViewById( R.id.hospital_activity_patient_reviews );
        patientReview.setTypeface( Typefaces.getRobotoBold( getBaseContext() ) );

        hospitalNoCommentText.setTypeface( Typefaces.getRobotoBold( getBaseContext() ) );

        hospitalRatingBar = (MaterialRatingBar) findViewById( R.id.hospital_activity_rating_bar );
        hospitalMail = (FloatingActionButton) findViewById( R.id.hospital_activity_mail_float_button );
        hospitalPhone = (FloatingActionButton) findViewById( R.id.hospital_activity_phone_float_button );

        hospitalMail.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( Intent.ACTION_VIEW );
                Uri data = Uri.parse( "mailto:" + hospitalEmail );
                intent.setData( data );
                startActivity( intent );
            }
        } );

        hospitalPhone.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( Intent.ACTION_DIAL, Uri.parse( "tel:" + hospitalTelephone ) );
                startActivity( intent );
            }
        } );

        Typeface hospitalNameTypeFace = Typeface.createFromAsset( getAssets(), "fonts/Roboto-Bold.ttf" );
        //hospitalNameText.setTypeface( hospitalNameTypeFace );
        hospitalNameText.setTypeface( Typefaces.getRobotoBold( getBaseContext() ) );
        Typeface typeface = Typeface.createFromAsset( getAssets(), "fonts/Lato-Light.ttf" );
        hospitalType.setTypeface( typeface );

        try {
            profilePicture = (ImageView) findViewById( R.id.hospital_activity_profile_image_view );
            bitmap = BitmapFactory.decodeResource( getResources(), R.drawable.hospital_sign );
            Bitmap circleBitmap = Bitmap.createBitmap( bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888 );
            BitmapShader shader = new BitmapShader( bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP );
            Paint paint = new Paint();
            paint.setShader( shader );
            paint.setAntiAlias( true );
            Canvas c = new Canvas( circleBitmap );
            c.drawCircle( bitmap.getWidth() / 2, bitmap.getHeight() / 2, bitmap.getWidth() / 2, paint );
            profilePicture.setImageBitmap( circleBitmap );
        } catch (Exception e) {
            Log.e( "UserHealthTree", e.getMessage() );
            Toast.makeText( this, e.getMessage(), Toast.LENGTH_LONG ).show();
        }
        fillHospitalAreas( hospitalId );
        getUserReviews(hospitalId);

        addFirstComment.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                hospitalReview = new FragmentAddHospitalReview( userId, hospitalId );
                hospitalReview.show( fragmentManager, "" );
            }
        } );

        ratingLayout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                hospitalReview = new FragmentAddHospitalReview( userId, hospitalId );
                hospitalReview.show( fragmentManager, "" );
            }
        } );

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById( R.id.hospital_location_map_fragment );
        mapFragment.getMapAsync( this );
    }

    private void fillHospitalAreas(final int hospitalId) {
        try {
            ApiClient.hospitalApi().searchByHospitalId( hospitalId ).enqueue( new Callback<Hospital>() {
                @Override
                public void onResponse(Call<Hospital> call, Response<Hospital> response) {
                    if (response.isSuccessful()) {
                        Hospital h = response.body().getHospital();
                        hospitalNameText.setText( h.getHospitalName() );
                        hospitalName = h.getHospitalName();

                        if (h.getHospitalType() == 0) {
                            hospitalType.setText( getBaseContext().getResources().getString( R.string.state_hospital ) );
                        } else {
                            hospitalType.setText( getBaseContext().getResources().getSystem().getString( R.string.private_hospital ) );
                        }
                        if (h.getTotalVoteNumber() != 0) {
                            hospitalRatingBar.setRating( (float) (h.getTotalScore() / h.getTotalVoteNumber()) );
                        } else {
                            hospitalRatingBar.setRating( 0 );
                        }
                        if (h.getTotalVoteNumber() != 0) {
                            viewFlipper.setDisplayedChild( 1 );
                        } else {
                            viewFlipper.setDisplayedChild( 2 );
                        }
                        hospitalTelephone = h.getPhoneNumber();
                        hospitalEmail = h.getMail();
                        hospitalAdress = h.getAdress();
                        addLocationTagOnMap( h.getLatitude(), h.getLongitude() );
                    }
                }

                @Override
                public void onFailure(Call<Hospital> call, Throwable t) {
                    Log.e( "UserTimeline", t.getMessage() );
                }
            } );
        } catch (Exception e) {
            Log.e( "UserHealthTree", e.getMessage() );
            Toast.makeText( this, e.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        try {
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.map_style ) );

            if (!success) {
            }
        } catch (Resources.NotFoundException e) {
        }
    }

    private void addLocationTagOnMap(String lat, String lon) {
        LatLng hospital = new LatLng( Double.valueOf( lat ), Double.valueOf( lon ) );
        LatLng camera = new LatLng( Double.valueOf( lat ) - 0.04, Double.valueOf( lon ) );
        mMap.moveCamera( CameraUpdateFactory.newLatLng( camera ) );
        mMap.animateCamera( CameraUpdateFactory.zoomTo( 10 ), 2000, null );
        Marker hospitalMarker = mMap.addMarker( new MarkerOptions()
                .position( hospital )
                //.title("Sydney")
                .snippet( hospitalAdress )
                .icon( BitmapDescriptorFactory.fromResource( R.drawable.icon_hospital_marker ) ) );
    }

    private void getUserReviews(final int hospitalId) {
        try {
            ApiClient.hospitalApi().getHospitalRates( hospitalId ).enqueue( new Callback<UserHospitalRate>() {
                @Override
                public void onResponse(Call<UserHospitalRate> call, Response<UserHospitalRate> response) {
                    if (response.isSuccessful()) {
                        mAdapter = new UserHospitalRateAdapter( userId, response.body().getUserHospitalRates(), getBaseContext() );
                        recyclerView.setHasFixedSize( true );
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager( getBaseContext() );
                        recyclerView.setLayoutManager( mLayoutManager );
                        recyclerView.setItemAnimator( new DefaultItemAnimator() );
                        recyclerView.setAdapter( mAdapter );
                        mAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<UserHospitalRate> call, Throwable t) {
                    Log.e( "UserTimeline", t.getMessage() );
                }
            } );
        } catch (Exception e) {
            Log.e( "UserHealthTree", e.getMessage() );
            Toast.makeText( this, e.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }
}
