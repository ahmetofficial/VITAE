// Developer: Ahmet Kaymak
// Date: 24.04.2017

package com.project.ui.main.healthtree.disease;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ahmetkaymak.vitae.R;
import com.project.core.diseasemodule.Disease;
import com.project.restservice.ApiClient;
import com.project.restservice.FullTextSearchRequest;
import com.project.ui.main.healthtree.disease.adapter.DiseaseSearchAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentDiseaseAddOne extends Fragment {

    private View fragmentDiseaseAddOne;
    private String query;

    private ArrayList<Disease> allDiseases;
    private DiseaseSearchAdapter mAdapter;
    private RecyclerView recyclerView;
    private ViewPager viewPager;

    public FragmentDiseaseAddOne(String query, ViewPager viewPager) {
        this.query = query;
        this.viewPager = viewPager;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentDiseaseAddOne = inflater.inflate( R.layout.fragment_recyclerview, container, false );
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Search Disease");
        return fragmentDiseaseAddOne;
    }

    public void fillDiseases(String query) {
        try {
            FullTextSearchRequest fullTextSearchRequest = new FullTextSearchRequest();
            fullTextSearchRequest.setSearchText( query );
            ApiClient.diseaseApi().getDiseaseByName( fullTextSearchRequest ).enqueue( new Callback<Disease>() {
                @Override
                public void onResponse(Call<Disease> call, Response<Disease> response) {
                    if (response.isSuccessful()) {
                        allDiseases = response.body().getDiseases();
                        if (allDiseases.isEmpty() == false) {
                            recyclerView = (RecyclerView) fragmentDiseaseAddOne.findViewById( R.id.recycler_view );
                            mAdapter = new DiseaseSearchAdapter( allDiseases, getContext(), viewPager );
                            recyclerView.setHasFixedSize( true );
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager( getContext() );
                            recyclerView.setLayoutManager( mLayoutManager );
                            recyclerView.setItemAnimator( new DefaultItemAnimator() );
                            recyclerView.setAdapter( mAdapter );
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                }

                @Override
                public void onFailure(Call<Disease> call, Throwable t) {
                    Toast.makeText( getContext(), t.getMessage(), Toast.LENGTH_LONG ).show();
                }
            } );
        } catch (Exception e) {
            Toast.makeText( getContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }
}
