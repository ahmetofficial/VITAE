// Developer: Ahmet Kaymak
// Date: 29.09.2017

package com.project.ui.location.adapter;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmetkaymak.vitae.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.project.core.generalhealthmodule.BloodAlarm;
import com.project.utils.Typefaces;

import java.util.ArrayList;
import java.util.List;

public class BloodAlarmAdapter extends RecyclerView.Adapter<BloodAlarmAdapter.MyViewHolder> {
    private List<BloodAlarm> bloodAlarms = new ArrayList<>();
    private static Context context;
    private LatLng userLocation;
    private GoogleMap mMap;

    public BloodAlarmAdapter(List<BloodAlarm> bloodAlarms, Context context, GoogleMap mMap, LatLng userLocation) {
        this.bloodAlarms = bloodAlarms;
        this.userLocation=userLocation;
        this.context = context;
        this.mMap = mMap;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView hospitalName;
        public RelativeLayout relativeLayout;
        public TextView distanceToLocation;
        public FloatingActionButton phoneFloatButton;
        public ImageView alertImage;
        public TextView bloodAlert;

        public MyViewHolder(final View view) {
            super( view );
            hospitalName = (TextView) view.findViewById( R.id.hospital_name );
            relativeLayout = (RelativeLayout) view.findViewById( R.id.relative_layout );
            distanceToLocation = (TextView) view.findViewById( R.id.distance_to_location );
            phoneFloatButton = (FloatingActionButton) view.findViewById( R.id.phone_float_button );
            alertImage = (ImageView) view.findViewById( R.id.alert_circle_image );
            bloodAlert = (TextView) view.findViewById( R.id.blood_alert );
        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from( parent.getContext() )
                .inflate( R.layout.item_blood_alarm, parent, false );
        return new MyViewHolder( itemView );
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final BloodAlarm bloodAlarm = bloodAlarms.get( position );
        try {
            //hospital name
            holder.hospitalName.setText( bloodAlarm.getHospital().getHospitalName() );
            holder.hospitalName.setTypeface( Typefaces.getRobotoMedium( context ) );
            holder.bloodAlert.setTypeface( Typefaces.getRobotoLight( context ) );
            //Location Info
            String hospitalLatitude = bloodAlarm.getHospital().getLatitude();
            String hospitalLongitude = bloodAlarm.getHospital().getLongitude();
            Location locationA = new Location("Hospital");
            locationA.setLatitude(Double.valueOf( hospitalLatitude ));
            locationA.setLongitude(Double.valueOf( hospitalLongitude ));
            Location locationB = new Location("User");
            locationB.setLatitude(userLocation.latitude);
            locationB.setLongitude(userLocation.longitude);
            float distanceToHospital= locationA.distanceTo(locationB);
            String distance= context.getString( R.string.distance_to_my_location)+" :"+ String.format("%.1f", distanceToHospital/1000)+" km";
            holder.distanceToLocation.setText( distance );
            holder.distanceToLocation.setTypeface( Typefaces.getRobotoLight( context ) );

            //alarm level
            int alarmLevel = bloodAlarm.getAlarmLevel();
            if (alarmLevel == 5) {
                holder.alertImage.setBackground( context.getDrawable( R.drawable.circle_red ) );
            } else if (alarmLevel == 4) {
                holder.alertImage.setBackground( context.getDrawable( R.drawable.circle_orange) );
            } else if (alarmLevel == 3) {
                holder.alertImage.setBackground( context.getDrawable( R.drawable.circle_yellow ) );
            } else if (alarmLevel == 2) {
                holder.alertImage.setBackground( context.getDrawable( R.drawable.circle_light_green ) );
            } else {
                holder.alertImage.setBackground( context.getDrawable( R.drawable.circle_dark_green ) );
            }

            if(bloodAlarm.getContactNumber()!=null){
                holder.phoneFloatButton.setVisibility( View.VISIBLE );
            }else{
                holder.phoneFloatButton.setVisibility( View.GONE );
            }

            //contact number
            holder.phoneFloatButton.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent( Intent.ACTION_DIAL, Uri.parse( "tel:" + bloodAlarm.getContactNumber() ) );
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity( intent );
                }
            } );
        } catch (Exception e) {
            Log.e( "UserHealthTree", e.getMessage() );
            Toast.makeText( context, e.getMessage(), Toast.LENGTH_LONG ).show();
        }

        holder.relativeLayout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double lat = Double.valueOf( bloodAlarm.getHospital().getLatitude() );
                double lon = Double.valueOf( bloodAlarm.getHospital().getLongitude() );
                LatLng camera = new LatLng( lat, lon );
                mMap.moveCamera( CameraUpdateFactory.newLatLng( camera ) );
                mMap.animateCamera( CameraUpdateFactory.zoomTo( 12 ), 2000, null );
            }
        } );


    }

    @Override
    public int getItemCount() {
        return bloodAlarms.size();
    }
}