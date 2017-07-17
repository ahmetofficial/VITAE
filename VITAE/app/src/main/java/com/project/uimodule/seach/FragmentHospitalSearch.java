// Developer: Ahmet Kaymak
// Date: 19.04.2017

package com.project.uimodule.seach;

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
import com.project.hospitalmodule.Hospital;
import com.project.restservice.ApiClient;
import com.project.uimodule.seach.adapter.HospitalSearchAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentHospitalSearch extends Fragment {

    private List<Hospital> hospitalSearchList = new ArrayList<>();
    private RecyclerView recyclerView;
    private HospitalSearchAdapter mAdapter;
    private View fragmentHospitalSearchView;
    private String query;

    public FragmentHospitalSearch() {
    }

    public FragmentHospitalSearch(String query) {
        this.query = query;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentHospitalSearchView = inflater.inflate( R.layout.fragment_recyclerview, container, false );
        listSearchResult( query );
        return fragmentHospitalSearchView;
    }

    public void listSearchResult(String query) {
        try {
            Hospital h = new Hospital();
            h.setHospitalName( query );
            ApiClient.hospitalApi().searchByHospitalName( h ).enqueue( new Callback<Hospital>() {
                @Override
                public void onResponse(Call<Hospital> call, Response<Hospital> response) {
                    if (response.isSuccessful()) {
                        hospitalSearchList = response.body().getHospitals();
                        recyclerView = (RecyclerView) fragmentHospitalSearchView.findViewById( R.id.recycler_view );
                        mAdapter = new HospitalSearchAdapter( hospitalSearchList, fragmentHospitalSearchView.getContext() );
                        recyclerView.setHasFixedSize( true );
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager( fragmentHospitalSearchView.getContext() );
                        recyclerView.setLayoutManager( mLayoutManager );
                        recyclerView.setItemAnimator( new DefaultItemAnimator() );
                        recyclerView.setAdapter( mAdapter );
                        mAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<Hospital> call, Throwable t) {
                    Toast.makeText( getContext(), t.getMessage(), Toast.LENGTH_LONG ).show();
                }
            } );
        } catch (Exception e) {
            Toast.makeText( getContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }
}
