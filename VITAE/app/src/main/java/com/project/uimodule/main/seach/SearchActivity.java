// Developer: Ahmet Kaymak
// Date: 16.04.2017

package com.project.uimodule.main.seach;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.lavie.users.R;
import com.project.hospitalmodule.Hospital;
import com.project.restservice.ApiClient;
import com.project.uimodule.main.seach.adapter.HospitalSearchAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    public static String query;
    private List<Hospital> hospitalSearchList = new ArrayList<>();
    private RecyclerView recyclerView;
    private HospitalSearchAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_search );
        listSearchResult(query);
    }

    private void listSearchResult(String query) {
        try {
            Hospital h = new Hospital();
            h.setHospitalName( query );
            ApiClient.hospitalApi().searchByHospitalName( h ).enqueue( new Callback<Hospital>() {
                @Override
                public void onResponse(Call<Hospital> call, Response<Hospital> response) {
                    if (response.isSuccessful()) {
                        hospitalSearchList = response.body().getHospitals();
                        recyclerView = (RecyclerView) findViewById( R.id.hospital_search_recycler_view );
                        mAdapter = new HospitalSearchAdapter( hospitalSearchList,getBaseContext() );
                        recyclerView.setHasFixedSize( true );
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager( getBaseContext() );
                        recyclerView.setLayoutManager( mLayoutManager );
                        recyclerView.setItemAnimator( new DefaultItemAnimator() );
                        recyclerView.setAdapter( mAdapter );
                        mAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<Hospital> call, Throwable t) {
                    Log.e( "UserTimeline", t.getMessage() );
                }
            } );
        } catch (Exception e) {
            Log.e( "UserTimeline", e.getMessage() );
        }
    }
}
