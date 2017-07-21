// Developer: Ahmet Kaymak
// Date: 20.07.2017

package com.project.uimodule.main.healthtree.disease.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmetkaymak.vitae.R;
import com.project.generalhealthmodule.UserTreatmentHistory;

import java.util.ArrayList;
import java.util.List;

public class DiseaseTreatmentsAdapter extends RecyclerView.Adapter<DiseaseTreatmentsAdapter.MyViewHolder> {

    private List<UserTreatmentHistory> treatments = new ArrayList<>();
    private Context context;

    public DiseaseTreatmentsAdapter(List<UserTreatmentHistory> treatments, Context context) {
        this.treatments = treatments;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView treatmentName;
        public ImageView treatmentIcon;

        public MyViewHolder(final View view) {
            super( view );
            treatmentName = (TextView) view.findViewById( R.id.title );
            treatmentIcon = (ImageView) view.findViewById( R.id.icon );
        }

    }

    @Override
    public DiseaseTreatmentsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from( parent.getContext() )
                .inflate( R.layout.item_treatment_row, parent, false );

        return new DiseaseTreatmentsAdapter.MyViewHolder( itemView );
    }

    @Override
    public void onBindViewHolder(DiseaseTreatmentsAdapter.MyViewHolder holder, int position) {
        try {
            holder.treatmentName.setText( treatments.get( position ).getTreatment().getTreatmentName() );
        } catch (Exception e) {
            Toast.makeText( context, e.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }

    @Override
    public int getItemCount() {
        return treatments.size();
    }
}
