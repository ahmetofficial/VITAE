// Developer: Ahmet Kaymak
// Date: 10.08.2017

package com.project.ui.patient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.ahmetkaymak.vitae.R;
import com.project.restservice.ApiClient;
import com.project.ui.patient.adapter.PatientFriendsAdapter;
import com.project.core.usermodule.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PatientFriendsActivity extends AppCompatActivity{

    private RecyclerView recyclerView;
    private PatientFriendsAdapter mAdapter;
    private ArrayList<User> friends;
    private String userId;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_friends );

        Intent myIntent = getIntent();
        userId = myIntent.getStringExtra( "userId" );

        recyclerView = (RecyclerView) findViewById( R.id.recycler_view);
        friendList( userId );
    }

    public void friendList(final String userId) {
        try {
            ApiClient.patientApi().getPatientFriends( userId ).enqueue( new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful()) {
                        friends = response.body().getUsers();
                        mAdapter = new PatientFriendsAdapter( friends, getBaseContext(), userId );
                        recyclerView.setHasFixedSize( true );
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager( getBaseContext() );
                        recyclerView.setLayoutManager( mLayoutManager );
                        recyclerView.setItemAnimator( new DefaultItemAnimator() );
                        recyclerView.setAdapter( mAdapter );
                        mAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText( getBaseContext(), t.getMessage(), Toast.LENGTH_LONG ).show();
                }
            } );
        } catch (Exception e) {
            Toast.makeText( getBaseContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }
}
