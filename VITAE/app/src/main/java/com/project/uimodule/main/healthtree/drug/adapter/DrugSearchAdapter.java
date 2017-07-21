// Developer: Ahmet Kaymak
// Date: 23.04.2017

package com.project.uimodule.main.healthtree.drug.adapter;

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
import com.project.drugmodule.Drug;
import com.project.uimodule.main.healthtree.drug.DrugAddActivity;
import com.project.uimodule.main.healthtree.drug.FragmentDrugAddFour;

import java.util.ArrayList;
import java.util.List;

public class DrugSearchAdapter extends RecyclerView.Adapter<DrugSearchAdapter.MyViewHolder> {

    private List<Drug> searchDrug = new ArrayList<>();
    private static Context context;
    private ViewPager viewPager;

    public DrugSearchAdapter(List<Drug> searchDrug, Context context, ViewPager viewPager) {
        this.searchDrug = searchDrug;
        this.context = context;
        this.viewPager = viewPager;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView searchItemName;
        public TextView searchItemId;
        private CardView drugCardView;

        public MyViewHolder(final View view) {
            super( view );
            searchItemName = (TextView) view.findViewById( R.id.search_item_name_text );
            searchItemId = (TextView) view.findViewById( R.id.search_item_id_text ) ;
            drugCardView = (CardView) view.findViewById( R.id.search_item );
        }

    }

    @Override
    public DrugSearchAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from( parent.getContext() )
                .inflate( R.layout.item_one_row_search, parent, false );

        return new DrugSearchAdapter.MyViewHolder( itemView );
    }

    @Override
    public void onBindViewHolder(final DrugSearchAdapter.MyViewHolder holder, final int position) {
        try {
            holder.searchItemName.setText( searchDrug.get( position ).getCommercialName() );
            holder.searchItemId.setText( String.valueOf(searchDrug.get( position ).getDrugId()) );
        } catch (Exception e) {
            Toast.makeText( context, e.getMessage(), Toast.LENGTH_LONG ).show();
        }

        holder.drugCardView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    DrugAddActivity.drugName=(String) holder.searchItemName.getText();
                    FragmentDrugAddFour.drugName=(String) holder.searchItemName.getText();
                    FragmentDrugAddFour.drugId =(Integer.parseInt(holder.searchItemId.getText().toString().trim()));
                    viewPager.setCurrentItem( 3 );
                } catch (Exception e) {
                    Toast.makeText( context, e.getMessage(), Toast.LENGTH_LONG ).show();
                }
            }
        } );
    }

    @Override
    public int getItemCount() {
        return searchDrug.size();
    }
}

