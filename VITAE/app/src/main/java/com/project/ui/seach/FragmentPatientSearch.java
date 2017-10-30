// Developer: Ahmet Kaymak
// Date: 19.04.2017

package com.project.ui.seach;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.ahmetkaymak.vitae.R;
import com.project.restservice.ApiClient;
import com.project.restservice.FullTextSearchRequest;
import com.project.ui.seach.adapter.PatientSearchAdapter;
import com.project.core.usermodule.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentPatientSearch extends Fragment {

    private String query;
    private String visitorUserId;
    private View fragmentUserSearchView;
    private ArrayList<User> userSearchList;
    private PatientSearchAdapter mAdapter;
    private RecyclerView recyclerView;
    private TextView dontFoundText;
    private ViewFlipper viewFlipper;

    public FragmentPatientSearch(String visitorUserId, String query) {
        this.query = query;
        this.visitorUserId = visitorUserId;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentUserSearchView = inflater.inflate( R.layout.fragment_recyclerview, container, false );
        viewFlipper = (ViewFlipper) fragmentUserSearchView.findViewById( R.id.view_flipper );
        dontFoundText = (TextView) fragmentUserSearchView.findViewById( R.id.dont_found_text );
        listSearchResult(visitorUserId, query );
        return fragmentUserSearchView;
    }

    public void listSearchResult(final String visitorUserId,String query) {
        try {
            FullTextSearchRequest fullTextSearchRequest = new FullTextSearchRequest();
            fullTextSearchRequest.setSearchText( query );
            ApiClient.patientApi().searchPatient( fullTextSearchRequest ).enqueue( new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful()) {
                        userSearchList = response.body().getUsers();
                        if(userSearchList.size()==0){
                            dontFoundText.setText( getString( R.string.user_doesnt_found ) );
                            viewFlipper.setDisplayedChild( 1 );
                        }else {
                            viewFlipper.setDisplayedChild( 0 );
                            recyclerView = (RecyclerView) fragmentUserSearchView.findViewById( R.id.recycler_view );
                            mAdapter = new PatientSearchAdapter( userSearchList, getContext(), visitorUserId );
                            recyclerView.setHasFixedSize( true );
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager( fragmentUserSearchView.getContext() );
                            recyclerView.setLayoutManager( mLayoutManager );
                            recyclerView.setItemAnimator( new DefaultItemAnimator() );
                            recyclerView.setAdapter( mAdapter );
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText( getContext(), t.getMessage(), Toast.LENGTH_LONG ).show();
                }
            } );
        } catch (Exception e) {
            Toast.makeText( getContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }
}
