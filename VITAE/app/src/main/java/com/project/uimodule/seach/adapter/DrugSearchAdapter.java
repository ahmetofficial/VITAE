package com.project.uimodule.seach.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmetkaymak.vitae.R;
import com.project.drugmodule.Drug;

import java.util.ArrayList;
import java.util.List;

public class DrugSearchAdapter extends RecyclerView.Adapter<DrugSearchAdapter.MyViewHolder> {
    private List<Drug> searchDrug = new ArrayList<>();
    private static Context context;

    public DrugSearchAdapter(List<Drug> searchDrug, Context context) {
        this.searchDrug = searchDrug;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView searchItemName;
        private CardView searchItemCardView;

        public MyViewHolder(final View view) {
            super( view );
            searchItemName = (TextView) view.findViewById( R.id.search_item_name_text );
            searchItemCardView = (CardView) view.findViewById( R.id.search_item );
        }
    }

    @Override
    public DrugSearchAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from( parent.getContext() )
                .inflate( R.layout.item_one_row_search, parent, false );

        return new DrugSearchAdapter.MyViewHolder( itemView );
    }

    @Override
    public void onBindViewHolder(DrugSearchAdapter.MyViewHolder holder, final int position) {
        try {
            holder.searchItemName.setText( searchDrug.get( position ).getCommercialName() );

        } catch (Exception e) {
            Toast.makeText( context, e.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }

    @Override
    public int getItemCount() {
        return searchDrug.size();
    }
}

