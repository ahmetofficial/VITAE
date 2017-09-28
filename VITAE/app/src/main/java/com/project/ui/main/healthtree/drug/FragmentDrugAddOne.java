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
import com.project.core.diseasemodule.Disease;
import com.project.core.generalhealthmodule.UserDiseaseHistory;
import com.project.restservice.ApiClient;
import com.project.ui.main.healthtree.drug.adapter.DrugUserDiseaseSearchAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentDrugAddOne extends Fragment {

    private View fragmentDrugAddOne;
    private String userId;
    private ViewPager viewPager;
    private ArrayList<UserDiseaseHistory> userDiseaseHistory;
    private DrugUserDiseaseSearchAdapter mAdapter;
    private RecyclerView recyclerView;

    public FragmentDrugAddOne(String userId, ViewPager viewPager) {
        this.userId = userId;
        this.viewPager = viewPager;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentDrugAddOne = inflater.inflate( R.layout.fragment_recyclerview, container, false );
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle( "" );
        getUserDiseaseHistory( userId );
        return fragmentDrugAddOne;
    }

    private void getUserDiseaseHistory(String userId) {
        try {
            ApiClient.userDiseaseHistoryApi().getUserDiseaseHistory( userId ).enqueue( new Callback<UserDiseaseHistory>() {
                @Override
                public void onResponse(Call<UserDiseaseHistory> call, Response<UserDiseaseHistory> response) {
                    if (response.isSuccessful()) {
                        userDiseaseHistory = response.body().getUserDiseaseHistories();
                        if (userDiseaseHistory.isEmpty() == false) {
                            List<Disease> userDiseases = new ArrayList<Disease>();
                            for (int i = 0; i < userDiseaseHistory.size(); i++) {
                                userDiseases.add( userDiseaseHistory.get( i ).getDisease() );
                            }
                            recyclerView = (RecyclerView) fragmentDrugAddOne.findViewById( R.id.recycler_view );
                            mAdapter = new DrugUserDiseaseSearchAdapter( userDiseases, getContext(), viewPager);
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
                public void onFailure(Call<UserDiseaseHistory> call, Throwable t) {
                    Toast.makeText( getActivity(), t.getMessage(), Toast.LENGTH_LONG ).show();
                }
            } );
        } catch (Exception e) {
            Toast.makeText( getContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }
}
