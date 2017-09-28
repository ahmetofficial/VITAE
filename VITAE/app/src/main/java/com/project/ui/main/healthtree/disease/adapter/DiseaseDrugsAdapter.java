// Developer: Ahmet Kaymak
// Date: 20.07.2017

package com.project.ui.main.healthtree.disease.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmetkaymak.vitae.R;
import com.project.core.generalhealthmodule.UserDrugUsageHistory;

import java.util.ArrayList;
import java.util.List;

public class DiseaseDrugsAdapter extends RecyclerView.Adapter<DiseaseDrugsAdapter.MyViewHolder> {

    private List<UserDrugUsageHistory> drugUsageHistory = new ArrayList<>();
    private Context context;

    public DiseaseDrugsAdapter(List<UserDrugUsageHistory> drugUsageHistory, Context context) {
        this.drugUsageHistory = drugUsageHistory;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView drugName;
        public ImageView drugIcon;

        public MyViewHolder(final View view) {
            super( view );
            drugName = (TextView) view.findViewById( R.id.title );
            drugIcon = (ImageView) view.findViewById( R.id.icon );
        }

    }

    @Override
    public DiseaseDrugsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from( parent.getContext() )
                .inflate( R.layout.item_treatment_row, parent, false );

        return new DiseaseDrugsAdapter.MyViewHolder( itemView );
    }

    @Override
    public void onBindViewHolder(DiseaseDrugsAdapter.MyViewHolder holder, int position) {
        try {
            holder.drugName.setText( drugUsageHistory.get( position ).getDrug().getCommercialName() );
        } catch (Exception e) {
            Toast.makeText( context, e.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }

    @Override
    public int getItemCount() {
        return drugUsageHistory.size();
    }
}
