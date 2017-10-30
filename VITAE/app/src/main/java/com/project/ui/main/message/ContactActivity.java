// Developer: Ahmet Kaymak
// Date: 10.08.2017

package com.project.ui.main.message;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmetkaymak.vitae.R;
import com.project.core.patientmodule.PatientForConversation;
import com.project.restservice.ApiClient;
import com.project.ui.main.message.adapter.ContactAdapter;
import com.project.utils.Typefaces;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ContactAdapter mAdapter;
    private ArrayList<PatientForConversation> friends;
    private String userId;
    private TextView contactNumber;
    private TextView contactText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_start_conversation );

        Intent myIntent = getIntent();
        userId = myIntent.getStringExtra( "userId" );

        recyclerView = (RecyclerView) findViewById( R.id.contact_recycler_view );
        contactNumber = (TextView) findViewById( R.id.contact_number );
        contactText = (TextView) findViewById( R.id.contact_text );
        contactNumber.setTypeface( Typefaces.getRobotoBold( getBaseContext() ) );
        contactText.setTypeface( Typefaces.getRobotoLight( getBaseContext() ) );
        contactText.setVisibility( View.GONE );

        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
        getSupportActionBar().setDisplayShowHomeEnabled( true );
        getSupportActionBar().setTitle( "" );

        toolbar.setNavigationOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        } );
        patientList( userId );
    }

    public void patientList(final String userId) {
        try {
            ApiClient.userApi().getFriendsWithConversationStatus( userId ).enqueue( new Callback<PatientForConversation>() {
                @Override
                public void onResponse(Call<PatientForConversation> call, Response<PatientForConversation> response) {
                    if (response.isSuccessful()) {
                        friends = response.body().getPatientsForConversation();
                        contactText.setVisibility( View.VISIBLE );
                        contactNumber.setText( String.valueOf( friends.size() ) );
                        mAdapter = new ContactAdapter( friends, getBaseContext(), userId );
                        recyclerView.setHasFixedSize( true );
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager( getBaseContext() );
                        recyclerView.setLayoutManager( mLayoutManager );
                        recyclerView.setItemAnimator( new DefaultItemAnimator() );
                        recyclerView.setAdapter( mAdapter );
                        mAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<PatientForConversation> call, Throwable t) {
                    Toast.makeText( getBaseContext(), t.getMessage(), Toast.LENGTH_LONG ).show();
                }
            } );
        } catch (Exception e) {
            Toast.makeText( getBaseContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }
}
