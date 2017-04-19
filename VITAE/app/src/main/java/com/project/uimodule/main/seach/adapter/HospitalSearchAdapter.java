// Developer: Ahmet Kaymak
// Date: 14.03.2017

package com.project.uimodule.main.seach.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lavie.users.R;
import com.project.hospitalmodule.Hospital;
import com.project.uimodule.hospitalpage.HospitalProfileActivity;

import java.util.ArrayList;
import java.util.List;

public class HospitalSearchAdapter extends RecyclerView.Adapter<HospitalSearchAdapter.MyViewHolder> {
    private List<Hospital> hospitals = new ArrayList<>();
    private static Context context;

    public HospitalSearchAdapter(List<Hospital> hospitals, Context context) {
        this.hospitals = hospitals;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView hospitalName, hospitalType, hospitalRating;
        private CardView hospitalCardView;

        public MyViewHolder(final View view) {
            super( view );
            hospitalName = (TextView) view.findViewById( R.id.search_item_hospital_name_text );
            hospitalType = (TextView) view.findViewById( R.id.search_item_hospital_type_text );
            hospitalRating = (TextView) view.findViewById( R.id.search_item_hospital_rating_text );
            hospitalCardView= (CardView) view.findViewById(R.id.hospital_search_item);
        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from( parent.getContext() )
                .inflate( R.layout.search_hospital_item, parent, false );

        return new MyViewHolder( itemView );
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        try {
            Hospital hospital = hospitals.get( position );
            holder.hospitalName.setText( hospital.getHospitalName() );
            String hospitalType;
            if (hospital.getHospitalType() == 0) {
                hospitalType = context.getResources().getString( R.string.state_hospital );
            } else {
                hospitalType = context.getResources().getSystem().getString( R.string.private_hospital );
            }
            holder.hospitalType.setText( hospitalType );
            holder.hospitalRating.setText( String.valueOf( hospital.getOverallScore() ) );
        } catch (Exception e) {
            Log.e( "UserHealthTree", e.getMessage() );
            Toast.makeText( context, e.getMessage(), Toast.LENGTH_LONG ).show();
        }

        holder.hospitalCardView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                try {
                    int hospitalId = hospitals.get( position ).getHospitalId();
                    HospitalProfileActivity.hospitalId=hospitalId;
                    Intent intent=new Intent( context, HospitalProfileActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }catch (Exception e) {
                    Log.e( "UserHealthTree", e.getMessage() );
                    Toast.makeText( context, e.getMessage(), Toast.LENGTH_LONG ).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return hospitals.size();
    }
}