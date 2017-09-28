// Developer: Ahmet Kaymak
// Date: 23.04.2017

package com.project.ui.main.healthtree.drug.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmetkaymak.vitae.R;
import com.project.core.treatmentmodule.Treatment;
import com.project.ui.main.healthtree.drug.DrugAddActivity;
import com.project.ui.main.healthtree.drug.FragmentDrugAddFour;

import java.util.ArrayList;
import java.util.List;

public class DrugUserTreatmentSearchAdapter extends RecyclerView.Adapter<DrugUserTreatmentSearchAdapter.MyViewHolder> {

    private List<Treatment> searchTreatment = new ArrayList<>();
    private static Context context;
    private ViewPager viewPager;

    public DrugUserTreatmentSearchAdapter(List<Treatment> searchTreatment, Context context, ViewPager viewPager) {
        this.searchTreatment = searchTreatment;
        this.context = context;
        this.viewPager = viewPager;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView searchItemName;
        public TextView searchItemId;
        private CardView treatmentCardView;

        public MyViewHolder(final View view) {
            super( view );
            searchItemName = (TextView) view.findViewById( R.id.search_item_name_text );
            searchItemId = (TextView) view.findViewById( R.id.search_item_id_text ) ;
            treatmentCardView = (CardView) view.findViewById( R.id.search_item );
        }

    }

    @Override
    public DrugUserTreatmentSearchAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from( parent.getContext() )
                .inflate( R.layout.item_one_row_search, parent, false );

        return new DrugUserTreatmentSearchAdapter.MyViewHolder( itemView );
    }

    @Override
    public void onBindViewHolder(final DrugUserTreatmentSearchAdapter.MyViewHolder holder, final int position) {
        try {
            holder.searchItemName.setText( searchTreatment.get( position ).getTreatmentName() );
            holder.searchItemId.setText( String.valueOf(searchTreatment.get( position ).getTreatmentId()) );
        } catch (Exception e) {
            Toast.makeText( context, e.getMessage(), Toast.LENGTH_LONG ).show();
        }

        holder.treatmentCardView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    DrugAddActivity.treatmentName=(String) holder.searchItemName.getText();
                    FragmentDrugAddFour.treatmentName=(String) holder.searchItemName.getText();
                    FragmentDrugAddFour.treatmentId = Integer.parseInt(holder.searchItemId.getText().toString().trim());
                    viewPager.setCurrentItem( 2 );
                } catch (Exception e) {
                    Toast.makeText( context, e.getMessage(), Toast.LENGTH_LONG ).show();
                }
            }
        } );
    }

    @Override
    public int getItemCount() {
        return searchTreatment.size();
    }
}
