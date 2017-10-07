// Developer: Ahmet Kaymak
// Date: 24.04.2017

package com.project.ui.main.healthtree.disease;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahmetkaymak.vitae.R;
import com.project.core.generalhealthmodule.UserDrugUsageHistory;
import com.project.ui.main.healthtree.disease.adapter.DiseaseDrugsAdapter;

import java.util.ArrayList;

public class DiseaseDrugFragment extends DialogFragment {

    private ArrayList<UserDrugUsageHistory> userDrugUsageHistory;
    private DiseaseDrugsAdapter diseaseDrugsAdapter;
    private RecyclerView diseaseCellDiseaseDrugRecyclerView;

    public DiseaseDrugFragment(ArrayList<UserDrugUsageHistory> userDrugUsageHistory) {
        this.userDrugUsageHistory = userDrugUsageHistory;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate( R.layout.fragment_disease_drugs, container, false);
        getDialog().setTitle(getString( R.string.my_drugs ));
        diseaseCellDiseaseDrugRecyclerView= (RecyclerView) rootView.findViewById( R.id.disease_drugs_recycler_view );
        diseaseCellDiseaseDrugRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        diseaseDrugsAdapter=new DiseaseDrugsAdapter(userDrugUsageHistory,getContext());
        diseaseCellDiseaseDrugRecyclerView.setAdapter(diseaseDrugsAdapter);
        diseaseDrugsAdapter.notifyDataSetChanged();
        return rootView;
    }
}

