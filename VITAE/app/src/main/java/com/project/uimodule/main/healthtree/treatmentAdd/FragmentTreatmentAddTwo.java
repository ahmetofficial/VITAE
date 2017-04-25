// Developer: Ahmet Kaymak
// Date: 24.04.2017

package com.project.uimodule.main.healthtree.treatmentAdd;

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

import com.lavie.users.R;
import com.project.restservice.ApiClient;
import com.project.restservice.FullTextSearchRequest;
import com.project.treatmentmodule.Treatment;
import com.project.uimodule.main.healthtree.treatmentAdd.adapter.TreatmentSearchAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentTreatmentAddTwo extends Fragment {

    private View fragmentTreatmentAddTwo;
    private String query;
    private ViewPager viewPager;
    private ArrayList<Treatment> treatments;
    private TreatmentSearchAdapter mAdapter;
    private RecyclerView recyclerView;

    public FragmentTreatmentAddTwo(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentTreatmentAddTwo = inflater.inflate( R.layout.fragment_recyclerview, container, false );
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle( "" );
        return fragmentTreatmentAddTwo;
    }

    public void fillTreatments(String query) {
        try {
            FullTextSearchRequest fullTextSearchRequest = new FullTextSearchRequest();
            fullTextSearchRequest.setSearchText( query );
            ApiClient.treatmentApi().getTreatmentByName( fullTextSearchRequest ).enqueue( new Callback<Treatment>() {
                @Override
                public void onResponse(Call<Treatment> call, Response<Treatment> response) {
                    if (response.isSuccessful()) {
                        treatments = response.body().getTreatments();
                        if (treatments.isEmpty() == false) {
                            recyclerView = (RecyclerView) fragmentTreatmentAddTwo.findViewById( R.id.recycler_view );
                            mAdapter = new TreatmentSearchAdapter( treatments, getContext(), viewPager);
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
                public void onFailure(Call<Treatment> call, Throwable t) {
                    Toast.makeText( getContext(), t.getMessage(), Toast.LENGTH_LONG ).show();
                }
            } );
        } catch (Exception e) {
            Toast.makeText( getContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }
}
