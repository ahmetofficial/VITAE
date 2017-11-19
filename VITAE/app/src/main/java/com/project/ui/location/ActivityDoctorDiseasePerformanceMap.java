// Developer: Ahmet Kaymak
// Date: 01.10.2017

package com.project.ui.location;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.ahmetkaymak.vitae.R;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.project.core.doctormodule.PatientDoctorRate;
import com.project.core.generalhealthmodule.UserDiseaseHistory;
import com.project.core.usermodule.UserLocation;
import com.project.restservice.ApiClient;
import com.project.restservice.serverResponse.ServerResponse;
import com.project.ui.location.adapter.DoctorDiseaseRankAdapter;
import com.project.ui.main.MenuActivity;
import com.project.utils.GPSTracker;
import com.project.utils.Typefaces;
import com.xw.repo.BubbleSeekBar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityDoctorDiseasePerformanceMap extends AppCompatActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, LocationListener, GoogleMap.OnCameraIdleListener {

    private MaterialSpinner diseaseSpinner;
    private ViewFlipper viewFlipper;
    private TextView warningText;
    private TextView plusText;
    private double latitude, longitude;
    private BubbleSeekBar distanceBar;
    private String userId;
    private String diseaseId;

    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private Marker mLocationMarker;
    private Location mLastLocation;
    private LocationRequest mLocationRequest;

    private boolean isGPSEnabled;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private TextView hospitalRanking;

    private RecyclerView recyclerView;
    private DoctorDiseaseRankAdapter mAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_doctor_disease_performance_map);

        Intent myIntent = getIntent();
        userId = myIntent.getStringExtra( "userId" );
        checkLocationPermission();

        diseaseSpinner = (MaterialSpinner) findViewById( R.id.disease_spinner );
        hospitalRanking = (TextView) findViewById( R.id.doctor_ranking );
        hospitalRanking.setTypeface( Typefaces.getRobotoBold( getBaseContext() ) );
        hospitalRanking.setText( getString( R.string.doctor_ranking ) );
        distanceBar = (BubbleSeekBar) findViewById( R.id.distance_level );
        distanceBar.setVisibility( View.GONE );
        warningText = (TextView) findViewById( R.id.warning_text );
        warningText.setTypeface( Typefaces.getRobotoLight( getBaseContext() ) );
        plusText = (TextView) findViewById( R.id.plus_text );
        viewFlipper = (ViewFlipper) findViewById( R.id.view_flipper );
        viewFlipper.setDisplayedChild( 1 );

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById( R.id.doctor_location_performace_map_fragment );
        mapFragment.getMapAsync( this );

        LocationManager locationManager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );

        try {
            isGPSEnabled = locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER );
        } catch (Exception ex) {
        }

        if (isGPSEnabled) {
            GPSTracker gpsTracker = new GPSTracker( getBaseContext() );
            Location location = gpsTracker.getLocation();
            distanceBar.setVisibility( View.VISIBLE );

            if(location!=null) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();

                try {
                    ApiClient.userDiseaseHistoryApi().getUserDiseaseHistory( userId ).enqueue( new Callback<UserDiseaseHistory>() {
                        @Override
                        public void onResponse(Call<UserDiseaseHistory> call, Response<UserDiseaseHistory> response) {
                            if (response.isSuccessful()) {
                                fillDiseaseSpinner( response.body().getUserDiseaseHistories() );
                            }
                        }

                        @Override
                        public void onFailure(Call<UserDiseaseHistory> call, Throwable t) {
                            Log.e( "UserHealthTree", t.getMessage() );
                            Toast.makeText( getBaseContext(), t.getMessage(), Toast.LENGTH_LONG ).show();
                        }
                    } );
                } catch (Exception e) {
                    Log.e( "UserHealthTree", e.getMessage() );
                    Toast.makeText( getBaseContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
                }
                try {
                    UserLocation userLocation=new UserLocation( );
                    userLocation.setUserId( userId );
                    userLocation.setLatitude( String.valueOf(latitude) );
                    userLocation.setLongitude( String.valueOf(longitude) );
                    ApiClient.userApi().saveUserLocation( userLocation ).enqueue( new Callback<ServerResponse>() {
                        @Override
                        public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                            if (response.isSuccessful()) {
                            }
                        }
                        @Override
                        public void onFailure(Call<ServerResponse> call, Throwable t) {
                            Log.e( "UserHealthTree", t.getMessage() );
                            Toast.makeText( getBaseContext(), t.getMessage(), Toast.LENGTH_LONG ).show();
                        }
                    } );
                } catch (Exception e) {
                    Log.e( "UserHealthTree", e.getMessage() );
                    Toast.makeText( getBaseContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
                }
            }

        } else {
            buildAlertMessageNoGps();
        }

        distanceBar.setOnProgressChangedListener( new BubbleSeekBar.OnProgressChangedListener() {
            @Override
            public void onProgressChanged(BubbleSeekBar bubbleSeekBar, final int progress, float progressFloat) {
                if(progress==50) {
                    plusText.setTextColor( getColor( R.color.colorPrimary ) );
                }
                else{
                    plusText.setTextColor( getColor( R.color.white ) );
                }
                if (diseaseId != null) {
                    try {
                        ApiClient.doctorApi().getDoctorRankingByDiseaseId( diseaseId ).enqueue( new Callback<PatientDoctorRate>() {
                            @Override
                            public void onResponse(Call<PatientDoctorRate> call, Response<PatientDoctorRate> response) {
                                if (response.isSuccessful()) {
                                    int distance = progress;
                                    ArrayList<PatientDoctorRate> doctors = response.body().getRates();
                                    recyclerView = (RecyclerView) findViewById( R.id.recycler_view );
                                    ArrayList<PatientDoctorRate> doctorsWithDistance = new ArrayList<PatientDoctorRate>();
                                    for (int i = 0; i < doctors.size(); i++) {
                                        Location hospital = new Location( "Hospital" );
                                        hospital.setLatitude( Double.valueOf( doctors.get( i ).getDoctor().getDoctorHaveHospitals().get( 0 ).getHospital().getLatitude() ) );
                                        hospital.setLongitude( Double.valueOf( doctors.get( i ).getDoctor().getDoctorHaveHospitals().get( 0 ).getHospital().getLongitude() ) );
                                        Location user = new Location( "User" );
                                        user.setLatitude( latitude );
                                        user.setLongitude( longitude );
                                        float userDistanceToHospital = hospital.distanceTo( user );
                                        userDistanceToHospital /= 1000;
                                        if (userDistanceToHospital < distance || distance == 50) {
                                            doctorsWithDistance.add( doctors.get( i ) );
                                        }
                                    }
                                    LatLng userLocation = new LatLng( latitude, longitude );
                                    mAdapter = new DoctorDiseaseRankAdapter( doctorsWithDistance, getBaseContext(), mMap, userLocation);
                                    recyclerView.setHasFixedSize( true );
                                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager( getBaseContext() );
                                    recyclerView.setLayoutManager( mLayoutManager );
                                    recyclerView.setItemAnimator( new DefaultItemAnimator() );
                                    recyclerView.setAdapter( mAdapter );
                                    mAdapter.notifyDataSetChanged();

                                    if (doctorsWithDistance.size() == 0) {
                                        viewFlipper.setDisplayedChild( 1 );
                                    } else {
                                        viewFlipper.setDisplayedChild( 0 );
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<PatientDoctorRate> call, Throwable t) {
                                Log.e( "UserHealthTree", t.getMessage() );
                                Toast.makeText( getBaseContext(), t.getMessage(), Toast.LENGTH_LONG ).show();
                            }
                        } );
                    } catch (Exception e) {
                        Log.e( "UserHealthTree", e.getMessage() );
                        Toast.makeText( getBaseContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
                    }
                }
            }

            @Override
            public void getProgressOnActionUp(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat) {

            }

            @Override
            public void getProgressOnFinally(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat) {

            }
        } );
    }

    private void fillDiseaseSpinner(final ArrayList<UserDiseaseHistory> diseaseHistory) {
        final List<String> userDiseases = new ArrayList();
        userDiseases.add( getString( R.string.select_disease ) );
        for (int i = 0; i < diseaseHistory.size(); i++) {
            userDiseases.add( diseaseHistory.get( i ).getDisease().getDiseaseName() );
        }
        diseaseSpinner.setItems( userDiseases );
        diseaseSpinner.setOnItemSelectedListener( new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                final int distance = distanceBar.getProgress();
                if(position!=0) {
                    diseaseId = diseaseHistory.get( position - 1 ).getDiseaseId();
                    mMap.clear();
                    try {
                        ApiClient.doctorApi().getDoctorRankingByDiseaseId( diseaseId ).enqueue( new Callback<PatientDoctorRate>() {
                            @Override
                            public void onResponse(Call<PatientDoctorRate> call, Response<PatientDoctorRate> response) {
                                if (response.isSuccessful()) {
                                    ArrayList<PatientDoctorRate> doctors = response.body().getRates();
                                    recyclerView = (RecyclerView) findViewById( R.id.recycler_view );
                                    ArrayList<PatientDoctorRate> doctorsWithDistance = new ArrayList<PatientDoctorRate>();
                                    for (int i = 0; i < doctors.size(); i++) {
                                        Location hospital = new Location( "Hospital" );
                                        hospital.setLatitude( Double.valueOf( doctors.get( i ).getDoctor().getDoctorHaveHospitals().get( 0 ).getHospital().getLatitude() ) );
                                        hospital.setLongitude( Double.valueOf( doctors.get( i ).getDoctor().getDoctorHaveHospitals().get( 0 ).getHospital().getLongitude() ) );
                                        Location user = new Location( "User" );
                                        user.setLatitude( latitude );
                                        user.setLongitude( longitude );
                                        float userDistanceToHospital = hospital.distanceTo( user );
                                        userDistanceToHospital /= 1000;
                                        if (userDistanceToHospital < distance || distance == 50) {
                                            doctorsWithDistance.add( doctors.get( i ) );
                                        }
                                    }
                                    LatLng userLocation = new LatLng( latitude, longitude );
                                    mAdapter = new DoctorDiseaseRankAdapter( doctorsWithDistance, getBaseContext(), mMap, userLocation);
                                    recyclerView.setHasFixedSize( true );
                                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager( getBaseContext() );
                                    recyclerView.setLayoutManager( mLayoutManager );
                                    recyclerView.setItemAnimator( new DefaultItemAnimator() );
                                    recyclerView.setAdapter( mAdapter );
                                    mAdapter.notifyDataSetChanged();

                                    if (doctorsWithDistance.size() == 0) {
                                        viewFlipper.setDisplayedChild( 1 );
                                    } else {
                                        viewFlipper.setDisplayedChild( 0 );
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<PatientDoctorRate> call, Throwable t) {
                                Log.e( "UserHealthTree", t.getMessage() );
                                Toast.makeText( getBaseContext(), t.getMessage(), Toast.LENGTH_LONG ).show();
                            }
                        } );
                    } catch (Exception e) {
                        Log.e( "UserHealthTree", e.getMessage() );
                        Toast.makeText( getBaseContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
                    }
                }else{
                    mMap.clear();
                    viewFlipper.setDisplayedChild( 1 );
                }
            }
        } );
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder( this );
        builder.setMessage( getString( R.string.gps_have_to_be_enabled ) )
                .setCancelable( false )
                .setPositiveButton( getString( R.string.yes ), new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity( new Intent( android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS ) );
                    }
                } )
                .setNegativeButton( getString( R.string.no ), new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                        startActivity( new Intent( ActivityDoctorDiseasePerformanceMap.this, MenuActivity.class ) );
                    }
                } );
        final AlertDialog alert = builder.create();
        alert.show();
    }

    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission( this, Manifest.permission.ACCESS_FINE_LOCATION )
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale( this,
                    Manifest.permission.ACCESS_FINE_LOCATION )) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder( this )
                        .setTitle( "Location Permission Needed" )
                        .setMessage( "This app needs the Location permission, please accept to use location functionality" )
                        .setPositiveButton( "OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions( ActivityDoctorDiseasePerformanceMap.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION );
                            }
                        } )
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions( this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION );
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission( this,
                            Manifest.permission.ACCESS_FINE_LOCATION )
                            == PackageManager.PERMISSION_GRANTED) {
                        mMap.setMyLocationEnabled( true );
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText( this, "permission denied", Toast.LENGTH_LONG ).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng camera = new LatLng( latitude, longitude );
        mMap.moveCamera( CameraUpdateFactory.newLatLng( camera ) );
        mMap.animateCamera( CameraUpdateFactory.zoomTo( 12 ), 2000, null );
        googleMap.setMapStyle( MapStyleOptions.loadRawResourceStyle( this, R.raw.map_style ) );
        buildGoogleApiClient();
        mMap.setMyLocationEnabled( true );
    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;

        LatLng camera = new LatLng( location.getLatitude() - 0.04, location.getLongitude() );
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom( camera, 10 );
        mMap.animateCamera( cameraUpdate );

        if (mLocationMarker != null) {
            mLocationMarker.remove();
        }
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates( mGoogleApiClient, (com.google.android.gms.location.LocationListener) this );
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    protected synchronized void buildGoogleApiClient() {
        // Use the GoogleApiClient.Builder class to create an instance of the
        // Google Play Services API client//
        mGoogleApiClient = new GoogleApiClient.Builder( this )
                .addConnectionCallbacks( this )
                .addApi( LocationServices.API )
                .build();

        // Connect to Google Play Services, by calling the connect() method//
        mGoogleApiClient.connect();
    }

    @Override
    public void onCameraIdle() {
        LatLngBounds bounds = mMap.getProjection().getVisibleRegion().latLngBounds;
        LatLng ne = bounds.northeast;
        ;
        LatLng sw = bounds.southwest;
    }

}
