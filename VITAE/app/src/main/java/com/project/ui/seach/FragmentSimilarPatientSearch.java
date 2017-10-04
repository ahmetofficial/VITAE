// Developer: Ahmet Kaymak
// Date: 14.03.2017

package com.project.ui.seach;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ahmetkaymak.vitae.R;
import com.project.restservice.ApiClient;
import com.project.ui.seach.adapter.SimilarUserSearchAdapter;
import com.project.core.usermodule.PatientSimilarityRequest;
import com.project.core.usermodule.PatientSimularityResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentSimilarPatientSearch extends Fragment {

    public FragmentSimilarPatientSearch(String visitorUserId,String query,int totalHealthItem) {
        this.query = query;
        this.visitorUserId = visitorUserId;
        this.totalHealthItem = totalHealthItem;
    }

    private int totalHealthItem;
    private String query;
    private String visitorUserId;
    private View fragmentSimilarUserSearchView;
    private ArrayList<PatientSimularityResponse> userSearchList;
    private SimilarUserSearchAdapter mAdapter;
    private RecyclerView recyclerView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentSimilarUserSearchView = inflater.inflate( R.layout.fragment_recyclerview, container, false );
        listSearchResult( query );
        return fragmentSimilarUserSearchView;
    }

    public void listSearchResult(String query) {
        try {
            PatientSimilarityRequest patientSimilarityRequest = new PatientSimilarityRequest();
            patientSimilarityRequest.setSearchText( query );
            patientSimilarityRequest.setUserId( visitorUserId );

            ApiClient.patientApi().searchSimilarUsers( patientSimilarityRequest ).enqueue( new Callback<PatientSimularityResponse>() {
                @Override
                public void onResponse(Call<PatientSimularityResponse> call, Response<PatientSimularityResponse> response) {
                    if (response.isSuccessful()) {
                        userSearchList = response.body().getSimilarUsers();
                        recyclerView = (RecyclerView) fragmentSimilarUserSearchView.findViewById( R.id.recycler_view );
                        mAdapter = new SimilarUserSearchAdapter( userSearchList, getContext(), visitorUserId, totalHealthItem );
                        recyclerView.setHasFixedSize( true );
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager( fragmentSimilarUserSearchView.getContext() );
                        recyclerView.setLayoutManager( mLayoutManager );
                        recyclerView.setItemAnimator( new DefaultItemAnimator() );
                        recyclerView.setAdapter( mAdapter );
                        mAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<PatientSimularityResponse> call, Throwable t) {
                    Toast.makeText( getContext(), t.getMessage(), Toast.LENGTH_LONG ).show();
                }
            } );
        } catch (Exception e) {
            Toast.makeText( getContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }
}
