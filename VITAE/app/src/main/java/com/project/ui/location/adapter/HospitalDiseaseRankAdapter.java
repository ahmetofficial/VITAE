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
import com.google.android.gms.maps.model.LatLng;
import com.project.core.hospitalmodule.UserHospitalRate;
import com.project.utils.Typefaces;

import java.util.ArrayList;
import java.util.List;

public class HospitalDiseaseRankAdapter extends RecyclerView.Adapter<HospitalDiseaseRankAdapter.MyViewHolder> {
    private List<UserHospitalRate> hospitalRates = new ArrayList<>();
    private static Context context;
    private GoogleMap mMap;
    private LatLng userLocation;

    public HospitalDiseaseRankAdapter(List<UserHospitalRate> hospitalRates, Context context, GoogleMap mMap, LatLng userLocation) {
        this.hospitalRates = hospitalRates;
        this.context = context;
        this.mMap = mMap;
        this.userLocation=userLocation;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public View hospitalRankView;
        public TextView hospitalName;
        public RelativeLayout relativeLayout;
        public TextView distanceToLocation;

        public MyViewHolder(final View view) {
            super( view );
            hospitalRankView = view.findViewById( R.id.hospital_rank_view );
            hospitalName = (TextView) view.findViewById( R.id.hospital_name );
            relativeLayout = (RelativeLayout) view.findViewById( R.id.relative_layout );
            distanceToLocation = (TextView) view.findViewById( R.id.distance_to_location );
        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from( parent.getContext() )
                .inflate( R.layout.item_hospital_ranking, parent, false );
        return new MyViewHolder( itemView );
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final UserHospitalRate rate = hospitalRates.get( position );
        try {
            holder.hospitalName.setText( rate.getHospital().getHospitalName() );
            holder.hospitalName.setTypeface( Typefaces.getRobotoBold( context ) );
            double overallRank = rate.getHospitalOverallScore();
            String hospitalLatitude = rate.getHospital().getLatitude();
            String hospitalLongitude = rate.getHospital().getLongitude();
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
                holder.hospitalRankView.setBackgroundColor( context.getColor( R.color.color_rank_4_5 ) );
            } else if (overallRank >= 3) {
                holder.hospitalRankView.setBackgroundColor( context.getColor( R.color.color_rank_3_4 ) );
            } else if (overallRank >= 2) {
                holder.hospitalRankView.setBackgroundColor( context.getColor( R.color.color_rank_2_3 ) );
            } else if (overallRank >= 1) {
                holder.hospitalRankView.setBackgroundColor( context.getColor( R.color.color_rank_1_2 ) );
            } else {
                holder.hospitalRankView.setBackgroundColor( context.getColor( R.color.color_rank_0_1 ) );
            }
        } catch (Exception e) {
            Log.e( "UserHealthTree", e.getMessage() );
            Toast.makeText( context, e.getMessage(), Toast.LENGTH_LONG ).show();
        }

        holder.relativeLayout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double lat = Double.valueOf( rate.getHospital().getLatitude() );
                double lon = Double.valueOf( rate.getHospital().getLongitude() );
                LatLng camera = new LatLng( lat, lon );
                mMap.moveCamera( CameraUpdateFactory.newLatLng( camera ) );
                mMap.animateCamera( CameraUpdateFactory.zoomTo( 12 ), 2000, null );
            }
        } );

    }

    @Override
    public int getItemCount() {
        return hospitalRates.size();
    }
}