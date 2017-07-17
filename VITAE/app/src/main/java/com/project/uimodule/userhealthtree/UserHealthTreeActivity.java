// Developer: Ahmet Kaymak
// Date: 30.04.2016

package com.project.uimodule.userhealthtree;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.ahmetkaymak.vitae.R;
import com.project.generalhealthmodule.UserDiseaseHistory;
import com.project.generalhealthmodule.UserDrugUsageHistory;
import com.project.restservice.ApiClient;
import com.project.uimodule.BaseActivity;
import com.project.uimodule.userhealthtree.adapter.TimelineDiseaseAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserHealthTreeActivity extends BaseActivity {

    public static String userId;
    private ArrayList<UserDiseaseHistory> userDiseaseHistoryList;
    private ArrayList<UserDrugUsageHistory> userDrugUsageHistoryList;

    //Timeline Rows List
    private ArrayList<TimelineRow> timelineDiseaseRowsList = new ArrayList<>();
    private TimelineDiseaseAdapter mAdapter;
    private RecyclerView diseaseRecyclerView;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_health_tree );

        diseaseRecyclerView = (RecyclerView) findViewById( R.id.activity_health_tree_timeline_recycler_view );
        //diseaseRecyclerView.addItemDecoration(new DividerItemDecoration(this));

        try {
            ApiClient.userDiseaseHistoryApi().getUserDiseaseHistory( userId ).enqueue( new Callback<UserDiseaseHistory>() {
                @Override
                public void onResponse(Call<UserDiseaseHistory> call, Response<UserDiseaseHistory> response) {
                    if (response.isSuccessful()) {
                        userDiseaseHistoryList = response.body().getUserDiseaseHistories();
                        //final String[] diseaseLevel = getResources().getStringArray( R.array.disease_level_array );
                        final String[] diseaseState = getResources().getStringArray( R.array.disease_state_array );
                        for (int i = 0; i < userDiseaseHistoryList.size(); i++) {
                            timelineDiseaseRowsList.add( new TimelineRow(
                                            userDiseaseHistoryList.get( i ).getDiseaseId(),
                                            userDiseaseHistoryList.get( i ).getDiseaseStartDate()
                                            ,userDiseaseHistoryList.get( i ).getDisease().getDiseaseName()
                                            ,diseaseState[userDiseaseHistoryList.get( i ).getDiseaseStateId()]
                                            , BitmapFactory.decodeResource(getResources(), R.drawable.icon_disease_red)
                                            , getColor( R.color.disease_color_light ), 10, 50, -1, 50 ) );

                        }

                        mAdapter = new TimelineDiseaseAdapter( getBaseContext(), getResources(),timelineDiseaseRowsList,true);
                        diseaseRecyclerView.setHasFixedSize(true);
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getBaseContext());
                        diseaseRecyclerView.setLayoutManager(mLayoutManager);
                        diseaseRecyclerView.setItemAnimator(new DefaultItemAnimator());
                        diseaseRecyclerView.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<UserDiseaseHistory> call, Throwable t) {
                    Log.e( "UserHealthTree", t.getMessage() );
                    Toast.makeText( getBaseContext(), t.getMessage(), Toast.LENGTH_LONG ).show();
                }
            } );
        } catch (Exception e) {
            Log.e( "UserHealthTree", e.getMessage() );
            Toast.makeText( getBaseContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }
}
