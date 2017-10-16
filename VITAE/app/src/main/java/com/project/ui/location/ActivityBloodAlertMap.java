// Developer: Ahmet Kaymak
// Date: 13.10.2017

package com.project.ui.location;

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
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.ahmetkaymak.vitae.Manifest;
import com.ahmetkaymak.vitae.R;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.project.core.generalhealthmodule.BloodAlarm;
import com.project.restservice.ApiClient;
import com.project.ui.location.adapter.BloodAlarmAdapter;
import com.project.ui.main.MenuActivity;
import com.project.utils.GPSTracker;
import com.project.utils.Typefaces;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityBloodAlertMap extends AppCompatActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, LocationListener, GoogleMap.OnCameraIdleListener {

    private MaterialSpinner bloodTypeSpinner;
    private ViewFlipper viewFlipper;
    private TextView warningText;
    private double latitude, longitude;
    private LatLng northEastCamera;
    private LatLng southWestCamera;
    private String userId;
    private int bloodTypeId;
    private RecyclerView recyclerView;
    private BloodAlarmAdapter mAdapter;

    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private Marker mLocationMarker;
    private Location mLastLocation;
    private LocationRequest mLocationRequest;

    private boolean isGPSEnabled;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private TextView hospitalRanking;
    private TextView header;

    private FragmentCreateBloodAlarm fragmentCreateBloodAlarm;

    private ImageView createBloodAlarmIcon;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_blood_alert_map );

        Intent myIntent = getIntent();
        userId = myIntent.getStringExtra( "userId" );

        createBloodAlarmIcon = (ImageView) findViewById( R.id.plus );
        header = (TextView) findViewById( R.id.header );
        header.setTypeface( Typefaces.getRobotoMedium( getBaseContext() ) );
        bloodTypeSpinner = (MaterialSpinner) findViewById( R.id.blood_spinner );
        bloodTypeSpinner.setItems( getString( R.string.select_blood_type ), "0 Rh+", "0 Rh-", "A Rh+", "A Rh-", "B Rh+", "B Rh-", "AB Rh+", "AB Rh-" );
        bloodTypeSpinner.setOnItemSelectedListener( new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                if (position != 0) {
                    bloodTypeId = position - 1;
                    mMap.clear();
                    try {
                        ApiClient.bloodApi().getBloodAlarmsByBloodType(bloodTypeId).enqueue( new Callback<BloodAlarm>() {
                            @Override
                            public void onResponse(Call<BloodAlarm> call, Response<BloodAlarm> response) {
                                if (response.isSuccessful()) {
                                    ArrayList<BloodAlarm> bloodAlarms = response.body().getBloodAlarms();
                                    recyclerView = (RecyclerView) findViewById( R.id.recycler_view );
                                    LatLng userLocation = new LatLng( latitude, longitude );
                                    mAdapter = new BloodAlarmAdapter( bloodAlarms, getBaseContext(), mMap, userLocation );
                                    recyclerView.setHasFixedSize( true );
                                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager( getBaseContext() );
                                    recyclerView.setLayoutManager( mLayoutManager );
                                    recyclerView.setItemAnimator( new DefaultItemAnimator() );
                                    recyclerView.setAdapter( mAdapter );
                                    mAdapter.notifyDataSetChanged();

                                    /*
                                    if (bloodAlarms.size() == 0) {
                                        viewFlipper.setDisplayedChild( 1 );
                                    } else {
                                        viewFlipper.setDisplayedChild( 0 );
                                    }
                                    */

                                    for (int i = 0; i < bloodAlarms.size(); i++) {
                                        double lat = Double.valueOf( bloodAlarms.get( i ).getHospital().getLatitude() );
                                        double lon = Double.valueOf( bloodAlarms.get( i ).getHospital().getLongitude() );
                                        LatLng hospitalLocation = new LatLng( lat, lon );
                                        if (bloodAlarms.get( i ).getAlarmLevel() == 1) {
                                            mMap.addMarker( new MarkerOptions()
                                                    .position( hospitalLocation )
                                                    .icon( BitmapDescriptorFactory.fromResource( R.drawable.hospital_ranking_marker_4_5 ) )
                                                    .title( bloodAlarms.get( i ).getHospital().getHospitalName() )
                                            );
                                        } else if (bloodAlarms.get( i ).getAlarmLevel() == 2) {
                                            mMap.addMarker( new MarkerOptions()
                                                    .position( hospitalLocation )
                                                    .icon( BitmapDescriptorFactory.fromResource( R.drawable.hospital_ranking_marker_3_4 ) )
                                                    .title( bloodAlarms.get( i ).getHospital().getHospitalName() )
                                            );
                                        } else if (bloodAlarms.get( i ).getAlarmLevel() == 3) {
                                            mMap.addMarker( new MarkerOptions()
                                                    .position( hospitalLocation )
                                                    .icon( BitmapDescriptorFactory.fromResource( R.drawable.hospital_ranking_marker_2_3 ) )
                                                    .title( bloodAlarms.get( i ).getHospital().getHospitalName() )
                                            );
                                        } else if (bloodAlarms.get( i ).getAlarmLevel() == 4) {
                                            mMap.addMarker( new MarkerOptions()
                                                    .position( hospitalLocation )
                                                    .icon( BitmapDescriptorFactory.fromResource( R.drawable.hospital_ranking_marker_1_2 ) )
                                                    .title( bloodAlarms.get( i ).getHospital().getHospitalName() )
                                            );
                                        } else {
                                            mMap.addMarker( new MarkerOptions()
                                                    .position( hospitalLocation )
                                                    .icon( BitmapDescriptorFactory.fromResource( R.drawable.hospital_ranking_marker_0_1 ) )
                                                    .title( bloodAlarms.get( i ).getHospital().getHospitalName() )
                                            );
                                        }
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<BloodAlarm> call, Throwable t) {
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
        } );

        createBloodAlarmIcon.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FragmentManager fm = getSupportFragmentManager();
                fragmentCreateBloodAlarm = new FragmentCreateBloodAlarm( userId );
                fragmentCreateBloodAlarm.show( fm, "Disease Treatments" );
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

        checkLocationPermission();
        LocationManager locationManager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );

        try {
            isGPSEnabled = locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER );
        } catch (Exception ex) {
        }

        if (isGPSEnabled) {
            GPSTracker gpsTracker = new GPSTracker( getBaseContext() );
            Location location = gpsTracker.getLocation();

            if (location != null) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById( R.id.blood_alert_map_fragment );
                mapFragment.getMapAsync( this );
            }
        } else {
            buildAlertMessageNoGps();
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

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder( this )
                .addConnectionCallbacks( this )
                .addApi( LocationServices.API )
                .build();
        mGoogleApiClient.connect();
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
                        startActivity( new Intent( ActivityBloodAlertMap.this, MenuActivity.class ) );
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
                                ActivityCompat.requestPermissions( ActivityBloodAlertMap.this,
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
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
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
    public void onCameraIdle() {
        LatLngBounds bounds = mMap.getProjection().getVisibleRegion().latLngBounds;
        northEastCamera = bounds.northeast;
        southWestCamera = bounds.southwest;
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
}