// Developer: Ahmet Kaymak
// Date: 24.04.2017

package com.project.ui.main.healthtree.drug;

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
import com.project.core.generalhealthmodule.UserTreatmentHistory;
import com.project.restservice.ApiClient;
import com.project.core.treatmentmodule.Treatment;
import com.project.ui.main.healthtree.drug.adapter.DrugUserTreatmentSearchAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentDrugAddTwo extends Fragment {

    private View fragmentDrugAddTwo;
    private ViewPager viewPager;
    private ArrayList<UserTreatmentHistory> userTreatmentHistory;
    private DrugUserTreatmentSearchAdapter mAdapter;
    private RecyclerView recyclerView;
    private String userId;

    public FragmentDrugAddTwo(String userId, ViewPager viewPager) {
        this.userId = userId;
        this.viewPager = viewPager;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentDrugAddTwo = inflater.inflate( R.layout.fragment_recyclerview, container, false );
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle( "" );
        getUserTreatmentHistory( userId );
        return fragmentDrugAddTwo;
    }

    private void getUserTreatmentHistory(String userId) {
        try {
            ApiClient.userTreatmentHistoryApi().getUserTreatmentHistory( userId ).enqueue( new Callback<UserTreatmentHistory>() {
                @Override
                public void onResponse(Call<UserTreatmentHistory> call, Response<UserTreatmentHistory> response) {
                    if (response.isSuccessful()) {
                        userTreatmentHistory = response.body().getUserTreatmentHistory();
                        if (userTreatmentHistory.isEmpty() == false) {
                            List<Treatment> userTreatments = new ArrayList<Treatment>();
                            for (int i = 0; i < userTreatmentHistory.size(); i++) {
                                userTreatments.add( userTreatmentHistory.get( i ).getTreatment() );
                            }
                            recyclerView = (RecyclerView) fragmentDrugAddTwo.findViewById( R.id.recycler_view );
                            mAdapter = new DrugUserTreatmentSearchAdapter( userTreatments, getContext(), viewPager );
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
                public void onFailure(Call<UserTreatmentHistory> call, Throwable t) {
                    Toast.makeText( getActivity(), t.getMessage(), Toast.LENGTH_LONG ).show();
                }
            } );
        } catch (Exception e) {
            Toast.makeText( getContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }
}
