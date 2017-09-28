package com.project.ui.main.healthtree.disease;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahmetkaymak.vitae.R;
import com.project.core.generalhealthmodule.UserTreatmentHistory;
import com.project.ui.main.healthtree.disease.adapter.DiseaseTreatmentsAdapter;

import java.util.ArrayList;

public class DiseaseTreatmentFragment extends DialogFragment {

    private ArrayList<UserTreatmentHistory> userTreatmentHistory;
    private DiseaseTreatmentsAdapter diseaseTreatmentsAdapter;
    private RecyclerView diseaseCellDiseaseTreatmentRecyclerView;

    public DiseaseTreatmentFragment(ArrayList<UserTreatmentHistory> userTreatmentHistory) {
        this.userTreatmentHistory = userTreatmentHistory;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate( R.layout.fragment_disease_treatments, container, false);
        getDialog().setTitle(getString( R.string.my_treatments ));
        diseaseCellDiseaseTreatmentRecyclerView= (RecyclerView) rootView.findViewById( R.id.disease_treatments_recycler_view );
        diseaseCellDiseaseTreatmentRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        diseaseTreatmentsAdapter=new DiseaseTreatmentsAdapter(userTreatmentHistory,getContext());
        diseaseCellDiseaseTreatmentRecyclerView.setAdapter(diseaseTreatmentsAdapter);
        diseaseTreatmentsAdapter.notifyDataSetChanged();
        return rootView;
    }
}

