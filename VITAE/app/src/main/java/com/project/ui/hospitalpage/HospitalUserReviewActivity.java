// Developer: Ahmet Kaymak
// Date: 30.09.2017

package com.project.ui.hospitalpage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmetkaymak.vitae.R;
import com.project.core.hospitalmodule.UserHospitalRate;
import com.project.restservice.ApiClient;
import com.project.ui.hospitalpage.adapter.UserHospitalRateAdapter;
import com.project.utils.Typefaces;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HospitalUserReviewActivity extends AppCompatActivity{

    private String userId;
    private int hospitalId;
    private String hospitalName;
    private TextView hospitalNameText;
    private UserHospitalRateAdapter mAdapter;
    private RecyclerView recyclerView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_hospital_user_reviews );

        Intent myIntent = getIntent();
        userId = myIntent.getStringExtra( "userId" );
        hospitalName = myIntent.getStringExtra( "hospitalName" );
        hospitalId = myIntent.getIntExtra( "hospitalId", 0 );

        hospitalNameText = (TextView) findViewById(R.id.hospital_review_hospital_name );
        hospitalNameText.setTypeface( Typefaces.getRobotoBold( getBaseContext() ) );
        hospitalNameText.setText( hospitalName );
        recyclerView= (RecyclerView) findViewById( R.id.recycler_view );
        getUserReviews( hospitalId );
    }

    private void getUserReviews(final int hospitalId) {
        try {
            ApiClient.hospitalApi().getHospitalRates( hospitalId ).enqueue( new Callback<UserHospitalRate>() {
                @Override
                public void onResponse(Call<UserHospitalRate> call, Response<UserHospitalRate> response) {
                    if (response.isSuccessful()) {
                        mAdapter = new UserHospitalRateAdapter( userId, response.body().getUserHospitalRates(), getBaseContext() );
                        recyclerView.setHasFixedSize( true );
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager( getBaseContext() );
                        recyclerView.setLayoutManager( mLayoutManager );
                        recyclerView.setItemAnimator( new DefaultItemAnimator() );
                        recyclerView.setAdapter( mAdapter );
                        mAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<UserHospitalRate> call, Throwable t) {
                    Log.e( "UserTimeline", t.getMessage() );
                }
            } );
        } catch (Exception e) {
            Log.e( "UserHealthTree", e.getMessage() );
            Toast.makeText( this, e.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }
}
