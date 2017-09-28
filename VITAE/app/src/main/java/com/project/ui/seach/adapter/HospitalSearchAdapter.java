// Developer: Ahmet Kaymak
// Date: 14.03.2017

package com.project.ui.seach.adapter;

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

import com.ahmetkaymak.vitae.R;
import com.project.core.hospitalmodule.Hospital;
import com.project.ui.hospitalpage.HospitalProfileActivity;

import java.util.ArrayList;
import java.util.List;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class HospitalSearchAdapter extends RecyclerView.Adapter<HospitalSearchAdapter.MyViewHolder> {
    private List<Hospital> hospitals = new ArrayList<>();
    private static Context context;
    private String userId;

    public HospitalSearchAdapter(String userId,List<Hospital> hospitals, Context context) {
        this.userId=userId;
        this.hospitals = hospitals;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView hospitalName, hospitalType;
        public MaterialRatingBar hospitalRating;
        private CardView hospitalCardView;

        public MyViewHolder(final View view) {
            super( view );
            hospitalName = (TextView) view.findViewById( R.id.search_item_hospital_name_text );
            hospitalType = (TextView) view.findViewById( R.id.search_item_hospital_type_text );
            hospitalRating = (MaterialRatingBar) view.findViewById( R.id.search_item_hospital_rating_bar);
            hospitalCardView= (CardView) view.findViewById(R.id.hospital_search_item);
        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from( parent.getContext() )
                .inflate( R.layout.item_search_hospital, parent, false );

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
            if(hospital.getTotalVoteNumber()!=0) {
                holder.hospitalRating.setRating( (float) (hospital.getTotalScore() / hospital.getTotalVoteNumber()) );
            }else{
                holder.hospitalRating.setRating( 0 );
            }
        } catch (Exception e) {
            Log.e( "UserHealthTree", e.getMessage() );
            Toast.makeText( context, e.getMessage(), Toast.LENGTH_LONG ).show();
        }

        holder.hospitalCardView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                try {
                    int hospitalId = hospitals.get( position ).getHospitalId();
                    Intent intent = new Intent( context, HospitalProfileActivity.class );
                    intent.putExtra( "userId", userId );
                    intent.putExtra( "hospitalId", hospitalId );
                    intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
                    context.startActivity( intent );
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