// Developer: Ahmet Kaymak
// Date: 23.04.2017

package com.project.uimodule.main.healthtree.treatmentAdd.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lavie.users.R;
import com.project.diseasemodule.Disease;
import com.project.uimodule.main.healthtree.treatmentAdd.FragmentTreatmentAddThree;
import com.project.uimodule.main.healthtree.treatmentAdd.TreatmentAddActivity;

import java.util.ArrayList;
import java.util.List;

public class TreatmentUserDiseaseSearchAdapter extends RecyclerView.Adapter<TreatmentUserDiseaseSearchAdapter.MyViewHolder> {

    private List<Disease> searchDisease = new ArrayList<>();
    private static Context context;
    private ViewPager viewPager;

    public TreatmentUserDiseaseSearchAdapter(List<Disease> searchDisease, Context context, ViewPager viewPager) {
        this.searchDisease = searchDisease;
        this.context = context;
        this.viewPager = viewPager;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView searchItemName;
        public TextView searchItemId;
        private CardView diseaseCardView;

        public MyViewHolder(final View view) {
            super( view );
            searchItemName = (TextView) view.findViewById( R.id.search_item_name_text );
            searchItemId = (TextView) view.findViewById( R.id.search_item_id_text ) ;
            diseaseCardView = (CardView) view.findViewById( R.id.search_item );
        }

    }

    @Override
    public TreatmentUserDiseaseSearchAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from( parent.getContext() )
                .inflate( R.layout.item_one_row_search, parent, false );

        return new TreatmentUserDiseaseSearchAdapter.MyViewHolder( itemView );
    }

    @Override
    public void onBindViewHolder(final TreatmentUserDiseaseSearchAdapter.MyViewHolder holder, final int position) {
        try {
            holder.searchItemName.setText( searchDisease.get( position ).getDiseaseName() );
            holder.searchItemId.setText( searchDisease.get( position ).getDiseaseId() );
        } catch (Exception e) {
            Toast.makeText( context, e.getMessage(), Toast.LENGTH_LONG ).show();
        }

        holder.diseaseCardView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    TreatmentAddActivity.diseaseName=(String) holder.searchItemName.getText();
                    FragmentTreatmentAddThree.diseaseName=(String) holder.searchItemName.getText();
                    FragmentTreatmentAddThree.diseaseId =(String) holder.searchItemId.getText();
                    viewPager.setCurrentItem( 1 );
                } catch (Exception e) {
                    Toast.makeText( context, e.getMessage(), Toast.LENGTH_LONG ).show();
                }
            }
        } );
    }

    @Override
    public int getItemCount() {
        return searchDisease.size();
    }
}
