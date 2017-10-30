// Developer: Ahmet Kaymak
// Date: 29.09.2017

package com.project.ui.location.adapter;

import android.content.Context;
import android.location.Location;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmetkaymak.vitae.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.project.core.doctormodule.PatientDoctorRate;
import com.project.utils.Typefaces;

import java.util.ArrayList;
import java.util.List;

public class DoctorDiseaseRankAdapter extends RecyclerView.Adapter<DoctorDiseaseRankAdapter.MyViewHolder> {
    private List<PatientDoctorRate> doctorRates = new ArrayList<>();
    private static Context context;
    private LatLng userLocation;
    private GoogleMap mMap;

    public DoctorDiseaseRankAdapter(List<PatientDoctorRate> doctorRates, Context context, GoogleMap mMap, LatLng userLocation) {
        this.doctorRates = doctorRates;
        this.context = context;
        this.mMap = mMap;
        this.userLocation=userLocation;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public View doctorRankView;
        public TextView hospitalName;
        public TextView doctorName;
        public TextView clinicName;
        public TextView distanceToLocation;
        public RelativeLayout relativeLayout;

        public MyViewHolder(final View view) {
            super( view );
            doctorRankView = view.findViewById( R.id.doctor_rank_view );
            hospitalName = (TextView) view.findViewById( R.id.hospital_name );
            doctorName = (TextView) view.findViewById( R.id.doctor_name );
            clinicName = (TextView) view.findViewById( R.id.clinic_name );
            distanceToLocation = (TextView) view.findViewById( R.id.distance_to_location );
            relativeLayout = (RelativeLayout) view.findViewById( R.id.relative_layout );
        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from( parent.getContext() )
                .inflate( R.layout.item_doctor_ranking, parent, false );
        return new MyViewHolder( itemView );
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final PatientDoctorRate rate = doctorRates.get( position );
        final double overallRank = rate.getDoctorOverallScore();
        try {
            holder.doctorName.setText( rate.getDoctor().getUserName() );
            holder.doctorName.setTypeface( Typefaces.getLatoLight( context ) );

            holder.hospitalName.setText( rate.getDoctor().getDoctorHaveHospitals().get( 0 ).getHospital().getHospitalName() );
            holder.hospitalName.setTypeface( Typefaces.getLatoLight( context ) );

            holder.clinicName.setText( rate.getDoctor().getDoctorHaveHospitals().get( 0 ).getClinic().getClinicName() );
            holder.clinicName.setTypeface( Typefaces.getLatoLight( context ) );

            //Location Info
            String hospitalLatitude = rate.getDoctor().getDoctorHaveHospitals().get( 0 ).getHospital().getLatitude();
            String hospitalLongitude = rate.getDoctor().getDoctorHaveHospitals().get( 0 ).getHospital().getLongitude();
            Location locationA = new Location( "Hospital" );
            locationA.setLatitude( Double.valueOf( hospitalLatitude ) );
            locationA.setLongitude( Double.valueOf( hospitalLongitude ) );
            Location locationB = new Location( "User" );
            locationB.setLatitude( userLocation.latitude );
            locationB.setLongitude( userLocation.longitude );
            float distanceToHospital = locationA.distanceTo( locationB );
            String distance = context.getString( R.string.distance_to_my_location ) + " :" + String.format( "%.1f", distanceToHospital / 1000 ) + " km";
            holder.distanceToLocation.setText( distance );
            holder.distanceToLocation.setTypeface( Typefaces.getRobotoLight( context ) );
            if (overallRank >= 4) {
                holder.doctorRankView.setBackgroundColor( context.getColor( R.color.color_rank_4_5 ) );
            } else if (overallRank >= 3) {
                holder.doctorRankView.setBackgroundColor( context.getColor( R.color.color_rank_3_4 ) );
            } else if (overallRank >= 2) {
                holder.doctorRankView.setBackgroundColor( context.getColor( R.color.color_rank_2_3 ) );
            } else if (overallRank >= 1) {
                holder.doctorRankView.setBackgroundColor( context.getColor( R.color.color_rank_1_2 ) );
            } else {
                holder.doctorRankView.setBackgroundColor( context.getColor( R.color.color_rank_0_1 ) );
            }
        } catch (Exception e) {
            Log.e( "UserHealthTree", e.getMessage() );
            Toast.makeText( context, e.getMessage(), Toast.LENGTH_LONG ).show();
        }

        holder.relativeLayout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double lat = Double.valueOf( rate.getDoctor().getDoctorHaveHospitals().get( 0 ).getHospital().getLatitude() );
                double lon = Double.valueOf( rate.getDoctor().getDoctorHaveHospitals().get( 0 ).getHospital().getLongitude() );
                String hospitalName=rate.getDoctor().getDoctorHaveHospitals().get( 0 ).getHospital().getHospitalName();
                LatLng hospitalLocation = new LatLng( lat, lon );

                if (overallRank >= 4) {
                    mMap.addMarker( new MarkerOptions()
                            .position( hospitalLocation )
                            .icon( BitmapDescriptorFactory.fromResource( R.drawable.hospital_ranking_marker_4_5 ) )
                            .title( hospitalName )
                    );
                } else if (overallRank >= 3) {
                    mMap.addMarker( new MarkerOptions()
                            .position( hospitalLocation )
                            .icon( BitmapDescriptorFactory.fromResource( R.drawable.hospital_ranking_marker_3_4 ) )
                            .title( hospitalName )
                    );
                } else if (overallRank >= 2) {
                    mMap.addMarker( new MarkerOptions()
                            .position( hospitalLocation )
                            .icon( BitmapDescriptorFactory.fromResource( R.drawable.hospital_ranking_marker_2_3 ) )
                            .title( hospitalName )
                    );
                } else if (overallRank >= 1) {
                    mMap.addMarker( new MarkerOptions()
                            .position( hospitalLocation )
                            .icon( BitmapDescriptorFactory.fromResource( R.drawable.hospital_ranking_marker_1_2 ) )
                            .title( hospitalName)
                    );
                } else {
                    mMap.addMarker( new MarkerOptions()
                            .position( hospitalLocation )
                            .icon( BitmapDescriptorFactory.fromResource( R.drawable.hospital_ranking_marker_0_1 ) )
                            .title( hospitalName )
                    );
                }
                LatLng camera = new LatLng( lat, lon );
                mMap.moveCamera( CameraUpdateFactory.newLatLng( camera ) );
                mMap.animateCamera( CameraUpdateFactory.zoomTo( 12 ), 2000, null );
            }
        } );

    }

    @Override
    public int getItemCount() {
        return doctorRates.size();
    }
}