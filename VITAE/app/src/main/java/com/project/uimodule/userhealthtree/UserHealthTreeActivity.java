// Developer: Ahmet Kaymak
// Date: 30.04.2016

package com.project.uimodule.userhealthtree;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.lavie.users.R;
import com.project.generalhealthmodule.UserDiseaseHistory;
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

    //Timeline Rows List
    private ArrayList<TimelineDiseaseRow> timelineDiseaseRowsList = new ArrayList<>();
    private ArrayAdapter<TimelineDiseaseRow> myAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_health_tree );

        try {
            ApiClient.userDiseaseHistoryApi().getUserDiseaseHistory( userId ).enqueue( new Callback<UserDiseaseHistory>() {
                @Override
                public void onResponse(Call<UserDiseaseHistory> call, Response<UserDiseaseHistory> response) {
                    if (response.isSuccessful()) {
                        userDiseaseHistoryList = response.body().getUserDiseaseHistories();
                        final String[] diseaseLevel = getResources().getStringArray( R.array.disease_level_array );
                        final String[] diseaseState = getResources().getStringArray( R.array.disease_state_array );
                        for (int i = 0; i < userDiseaseHistoryList.size(); i++) {
                            timelineDiseaseRowsList.add(
                                    new TimelineDiseaseRow(
                                            userDiseaseHistoryList.get( i ).getDiseaseId()
                                            ,userDiseaseHistoryList.get( i ).getDiseaseStartDate()
                                            ,userDiseaseHistoryList.get( i ).getDisease().getDiseaseName()
                                            ,diseaseState[userDiseaseHistoryList.get( i ).getDiseaseStateId()]
                                            , BitmapFactory.decodeResource(getResources(), R.drawable.icon_disease_red)
                                            , getColor( R.color.disease_color_light )
                                            , 10
                                            , 50
                                            , -1
                                            , 50
                                    )
                            );

                            fillTimelineWithDisease( timelineDiseaseRowsList );
                        }
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

    private void fillTimelineWithDisease(final ArrayList<TimelineDiseaseRow> timelineDiseaseRowsList) {

        myAdapter = new TimelineDiseaseAdapter( this, 0, timelineDiseaseRowsList, true );
        ListView myListView = (ListView) findViewById( R.id.activity_health_tree_timeline_list_view );
        myListView.setAdapter( myAdapter );

        myListView.setOnScrollListener( new AbsListView.OnScrollListener() {
            private int currentVisibleItemCount;
            private int currentScrollState;
            private int currentFirstVisibleItem;
            private int totalItem;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                this.currentScrollState = scrollState;
                this.isScrollCompleted();
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                this.currentFirstVisibleItem = firstVisibleItem;
                this.currentVisibleItemCount = visibleItemCount;
                this.totalItem = totalItemCount;
            }

            private void isScrollCompleted() {
                if (totalItem - currentFirstVisibleItem == currentVisibleItemCount && this.currentScrollState == SCROLL_STATE_IDLE) {
                    for (int i = 0; i < 15; i++) {
                        //myAdapter.add(}
                    }
                }
            }
        } );

        AdapterView.OnItemClickListener adapterListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TimelineDiseaseRow row = timelineDiseaseRowsList.get( position );
            }
        };
        myListView.setOnItemClickListener( adapterListener );
    }
}
